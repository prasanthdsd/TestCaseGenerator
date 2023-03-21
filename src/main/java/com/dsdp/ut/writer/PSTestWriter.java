package com.dsdp.ut.writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.cornutum.tcases.io.IndentedWriter;
import org.cornutum.tcases.openapi.resolver.RequestCase;
import org.cornutum.tcases.openapi.testwriter.AnnotatedJavaTestWriter;
import org.cornutum.tcases.openapi.testwriter.JavaTestTarget;
import org.cornutum.tcases.openapi.testwriter.TestCaseWriter;
import org.cornutum.tcases.openapi.testwriter.TestSource;
import org.cornutum.tcases.openapi.testwriter.TestWriterException;

import com.dsdp.ut.ps.model.Item;
import com.dsdp.ut.ps.model.PSCollection;
import com.dsdp.ut.ps.model.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PSTestWriter extends AnnotatedJavaTestWriter {
    public PSTestWriter(TestCaseWriter testCaseWriter) {
        super(testCaseWriter);
    }

    @Override
    protected void writeTestAnnotationDependencies(JavaTestTarget javaTestTarget, String s, IndentedWriter indentedWriter) {

    }

    @Override
    protected void writeTestAnnotation(JavaTestTarget javaTestTarget, String s, RequestCase requestCase, IndentedWriter indentedWriter) {

    }

    @Override
    protected String getTestName(String baseName) {
        String[] words = baseName.split("\\W+");
        return (String) IntStream.range(0, words.length).mapToObj((i) -> {
            return i == 0 ? WordUtils.capitalize(words[i]) : WordUtils.capitalizeFully(words[i]);
        }).collect(Collectors.joining(""));
    }

    protected File getTargetFile(JavaTestTarget target, String testName) {
        File targetFile = target.getFile();
        File targetDir = target.getDir();
        targetFile = targetFile == null && targetDir == null ? null : (targetDir == null ? targetFile : (targetFile == null ? new File(targetDir, testName.replaceAll("[/<>|:& \\\\]+", "-")) : (targetFile.isAbsolute() ? targetFile : new File(targetDir, targetFile.getPath()))));
        return targetFile != null && StringUtils.isBlank(FilenameUtils.getExtension(targetFile.getName())) ? new File(targetFile.getParentFile(), String.format("%s.json", FilenameUtils.getBaseName(targetFile.getName()))) : targetFile;
    }

    @Override
    protected void writeOpening(JavaTestTarget javaTestTarget, String s, IndentedWriter indentedWriter) {

    }

    @Override
    protected void writeDependencies(JavaTestTarget javaTestTarget, String s, IndentedWriter indentedWriter) {

    }

    @Override
    protected void writeDeclarations(JavaTestTarget javaTestTarget, String s, IndentedWriter indentedWriter) {

    }

    @Override
    protected void writeClosing(JavaTestTarget javaTestTarget, String s, IndentedWriter indentedWriter) {

    }

    public void writeTest(TestSource source, JavaTestTarget target) {
        String testName = this.getTestName(source, target);
        File targetFile = this.getTargetFile(target, testName);
        File targetDir = (File) Optional.ofNullable(targetFile).flatMap((file) -> {
            return Optional.ofNullable(file.getParentFile());
        }).map(File::getAbsoluteFile).orElse((File) null);
        if (targetDir != null && !targetDir.exists() && !targetDir.mkdirs()) {
            throw new TestWriterException(String.format("Can't create targetDir=%s", targetDir));
        } else {
            ObjectMapper mapper = new ObjectMapper();
            PSTestCaseWriter writer = new PSTestCaseWriter();
            PSCollection collection = new PSCollection();
            writer.writeInfo(target.getName(), collection);
            List<Object> items = new ArrayList<>();
            source.getRequestCases().stream().forEach(x -> {
                Item item = new Item();
                item.setName(this.getMethodName(x));
                Request request = new Request();
                request.setMethod(x.getOperation());
                writer.writeUrl(x, request);
                writer.writeHeader(x, request, "Accept", "*/*");
                writer.writeBody(x, request);
                item.setRequest(request);
                items.add(item);
                writer.writeVariable("baseUrl", x.getServer().toString(), "string", collection);
            });

            collection.setItem(items);

            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(targetFile, collection);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
