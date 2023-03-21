package com.dsdp.ut.writer;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.cornutum.tcases.io.IndentedWriter;
import org.cornutum.tcases.openapi.resolver.RequestCase;
import org.cornutum.tcases.openapi.test.MediaRange;
import org.cornutum.tcases.openapi.testwriter.BaseTestCaseWriter;
import org.cornutum.tcases.openapi.testwriter.TestWriterException;
import org.cornutum.tcases.openapi.testwriter.encoder.DataValueConverter;

import com.dsdp.ut.ps.model.Header;
import com.dsdp.ut.ps.model.Info;
import com.dsdp.ut.ps.model.Options;
import com.dsdp.ut.ps.model.PSCollection;
import com.dsdp.ut.ps.model.Query;
import com.dsdp.ut.ps.model.Raw;
import com.dsdp.ut.ps.model.Request;
import com.dsdp.ut.ps.model.Url;
import com.dsdp.ut.ps.model.Variable;

public class PSTestCaseWriter extends BaseTestCaseWriter {

    public void writeHeader(RequestCase requestCase, Request request, String key, String value) {
        Header header = new Header();
        header.setKey(key);
        header.setValue(value);
        List<Header> headers = new ArrayList<>();
        if (request.getHeader() != null) {
            headers = (List<Header>) request.getHeader();
        }
        headers.add(header);
        request.setHeader(headers);
    }

    public void writeUrl(RequestCase requestCase, Request request) {
        Url url = new Url();
        url.setRaw("{{baseUrl}}" + requestCase.getPath());
        url.setHost("{{baseUrl}}");
        url.setPath(Arrays.stream(requestCase.getPath().split("/")).filter(p -> StringUtils.isNotBlank(p)).collect(Collectors.toList()));
        writeQuery(requestCase, url);
        request.setUrl(url);
    }

    public void writeQuery(RequestCase requestCase, Url url) {
        Optional.ofNullable(requestCase.getParams()).ifPresent((param) -> {
            List<Query> qParams = new ArrayList<>();
            param.forEach(q -> {
                Query query = new Query();
                query.setKey(q.getName());
                query.setValue(q.getValue() != null ?
                        (q.getValue().getValue() != null ?
                                q.getValue().getValue().toString()
                                : q.getValue().getValue())
                        : null);
                qParams.add(query);
            });
            url.setQuery(qParams);
        });
    }

    public void writeBody(RequestCase requestCase, Request request) {
        Optional.ofNullable(requestCase.getBody()).ifPresent((body) -> {
            Optional.ofNullable(body.getValue()).ifPresent((value) -> {
                MediaRange mediaType = MediaRange.of(body.getMediaType());
                writeHeader(requestCase, request, "Content-Type", mediaType.base());
                Properties props = new Properties();
                props.put("mode", "raw");
                if ("application/octet-stream".equals(mediaType.base())) {
                    //this.writeBodyBinary(testName, value, targetWriter);
                } else if ("application/x-www-form-urlencoded".equals(mediaType.base())) {
                    //this.writeBodyForm(testName, body, targetWriter);
                } else if ("multipart/form-data".equals(mediaType.base())) {
                    //this.writeBodyMultipart(testName, body, targetWriter);
                } else {
                    props.put("raw", ((DataValueConverter) this.getConverter(mediaType).orElseThrow(() -> {
                        return new TestWriterException(String.format("No serializer defined for mediaType=%s", mediaType));
                    })).convert(value));
                }
                props.put("options", Options.builder().raw(Raw.builder().language("json").build()).build());
                request.setBody(props);
            });
        });
    }

    @Override
    public void writeDependencies(String s, IndentedWriter indentedWriter) {

    }

    @Override
    public void writeDeclarations(String s, IndentedWriter indentedWriter) {

    }

    @Override
    public void writeTestCase(String s, URI uri, RequestCase requestCase, IndentedWriter indentedWriter) {

    }

    @Override
    public void writeClosing(String s, IndentedWriter indentedWriter) {

    }

    public void writeVariable(String key, String value, String type, PSCollection collection) {
        Variable variable = new Variable();
        variable.setKey(key);
        variable.setValue(value);
        variable.setType(Variable.Type.fromValue(type));
        collection.setVariable(Arrays.asList(variable));
    }

    public void writeInfo(String name, PSCollection collection) {
        Info info = new Info();
        info.setPostmanId(UUID.randomUUID().toString());
        info.setName(name);
        info.setSchema("https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        collection.setInfo(info);
    }
}
