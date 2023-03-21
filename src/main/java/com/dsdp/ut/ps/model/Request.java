
package com.dsdp.ut.ps.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Request
 * <p>
 * A request represents an HTTP request. If a string, the string is assumed to be the request URL and the method is assumed to be 'GET'.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "auth",
    "proxy",
    "certificate",
    "method",
    "description",
    "header",
    "body"
})
@Generated("jsonschema2pojo")
public class Request {

    /**
     * Url
     * <p>
     * If object, contains the complete broken-down URL for this request. If string, contains the literal request URL.
     * 
     */
    @JsonProperty("url")
    @JsonPropertyDescription("If object, contains the complete broken-down URL for this request. If string, contains the literal request URL.")
    private Url url;
    @JsonProperty("auth")
    private Object auth;
    /**
     * Proxy Config
     * <p>
     * Using the Proxy, you can configure your custom proxy into the postman for particular url match
     * 
     */
    @JsonProperty("proxy")
    @JsonPropertyDescription("Using the Proxy, you can configure your custom proxy into the postman for particular url match")
    private ProxyConfig proxy;
    /**
     * Certificate
     * <p>
     * A representation of an ssl certificate
     * 
     */
    @JsonProperty("certificate")
    @JsonPropertyDescription("A representation of an ssl certificate")
    private Certificate certificate;
    @JsonProperty("method")
    private Object method;
    /**
     * A Description can be a raw text, or be an object, which holds the description along with its format.
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("A Description can be a raw text, or be an object, which holds the description along with its format.")
    private Object description;
    @JsonProperty("header")
    private Object header;
    @JsonProperty("body")
    private Object body;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * Url
     * <p>
     * If object, contains the complete broken-down URL for this request. If string, contains the literal request URL.
     * 
     */
    @JsonProperty("url")
    public Url getUrl() {
        return url;
    }

    /**
     * Url
     * <p>
     * If object, contains the complete broken-down URL for this request. If string, contains the literal request URL.
     * 
     */
    @JsonProperty("url")
    public void setUrl(Url url) {
        this.url = url;
    }

    @JsonProperty("auth")
    public Object getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(Object auth) {
        this.auth = auth;
    }

    /**
     * Proxy Config
     * <p>
     * Using the Proxy, you can configure your custom proxy into the postman for particular url match
     * 
     */
    @JsonProperty("proxy")
    public ProxyConfig getProxy() {
        return proxy;
    }

    /**
     * Proxy Config
     * <p>
     * Using the Proxy, you can configure your custom proxy into the postman for particular url match
     * 
     */
    @JsonProperty("proxy")
    public void setProxy(ProxyConfig proxy) {
        this.proxy = proxy;
    }

    /**
     * Certificate
     * <p>
     * A representation of an ssl certificate
     * 
     */
    @JsonProperty("certificate")
    public Certificate getCertificate() {
        return certificate;
    }

    /**
     * Certificate
     * <p>
     * A representation of an ssl certificate
     * 
     */
    @JsonProperty("certificate")
    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @JsonProperty("method")
    public Object getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(Object method) {
        this.method = method;
    }

    /**
     * A Description can be a raw text, or be an object, which holds the description along with its format.
     * 
     */
    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    /**
     * A Description can be a raw text, or be an object, which holds the description along with its format.
     * 
     */
    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("header")
    public Object getHeader() {
        return header;
    }

    @JsonProperty("header")
    public void setHeader(Object header) {
        this.header = header;
    }

    @JsonProperty("body")
    public Object getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(Object body) {
        this.body = body;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Request.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("auth");
        sb.append('=');
        sb.append(((this.auth == null)?"<null>":this.auth));
        sb.append(',');
        sb.append("proxy");
        sb.append('=');
        sb.append(((this.proxy == null)?"<null>":this.proxy));
        sb.append(',');
        sb.append("certificate");
        sb.append('=');
        sb.append(((this.certificate == null)?"<null>":this.certificate));
        sb.append(',');
        sb.append("method");
        sb.append('=');
        sb.append(((this.method == null)?"<null>":this.method));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("header");
        sb.append('=');
        sb.append(((this.header == null)?"<null>":this.header));
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null)?"<null>":this.body));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.proxy == null)? 0 :this.proxy.hashCode()));
        result = ((result* 31)+((this.method == null)? 0 :this.method.hashCode()));
        result = ((result* 31)+((this.auth == null)? 0 :this.auth.hashCode()));
        result = ((result* 31)+((this.certificate == null)? 0 :this.certificate.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.header == null)? 0 :this.header.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Request) == false) {
            return false;
        }
        Request rhs = ((Request) other);
        return ((((((((((this.proxy == rhs.proxy)||((this.proxy!= null)&&this.proxy.equals(rhs.proxy)))&&((this.method == rhs.method)||((this.method!= null)&&this.method.equals(rhs.method))))&&((this.auth == rhs.auth)||((this.auth!= null)&&this.auth.equals(rhs.auth))))&&((this.certificate == rhs.certificate)||((this.certificate!= null)&&this.certificate.equals(rhs.certificate))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.header == rhs.header)||((this.header!= null)&&this.header.equals(rhs.header))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
    }

}
