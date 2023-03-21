
package com.dsdp.ut.ps.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
 * Folder
 * <p>
 * One of the primary goals of Postman is to organize the development of APIs. To this end, it is necessary to be able to group requests together. This can be achived using 'Folders'. A folder just is an ordered set of requests.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "variable",
    "item",
    "event",
    "auth",
    "protocolProfileBehavior"
})
@Generated("jsonschema2pojo")
public class ItemGroup {

    /**
     * A folder's friendly name is defined by this field. You would want to set this field to a value that would allow you to easily identify this folder.
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("A folder's friendly name is defined by this field. You would want to set this field to a value that would allow you to easily identify this folder.")
    private String name;
    /**
     * A Description can be a raw text, or be an object, which holds the description along with its format.
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("A Description can be a raw text, or be an object, which holds the description along with its format.")
    private Object description;
    /**
     * Variable List
     * <p>
     * Collection variables allow you to define a set of variables, that are a *part of the collection*, as opposed to environments, which are separate entities.
     * *Note: Collection variables must not contain any sensitive information.*
     * 
     */
    @JsonProperty("variable")
    @JsonPropertyDescription("Collection variables allow you to define a set of variables, that are a *part of the collection*, as opposed to environments, which are separate entities.\n*Note: Collection variables must not contain any sensitive information.*")
    private List<Variable> variable = new ArrayList<Variable>();
    /**
     * Items are entities which contain an actual HTTP request, and sample responses attached to it. Folders may contain many items.
     * (Required)
     * 
     */
    @JsonProperty("item")
    @JsonPropertyDescription("Items are entities which contain an actual HTTP request, and sample responses attached to it. Folders may contain many items.")
    private List<Object> item = new ArrayList<Object>();
    /**
     * Event List
     * <p>
     * Postman allows you to configure scripts to run when specific events occur. These scripts are stored here, and can be referenced in the collection by their ID.
     * 
     */
    @JsonProperty("event")
    @JsonPropertyDescription("Postman allows you to configure scripts to run when specific events occur. These scripts are stored here, and can be referenced in the collection by their ID.")
    private List<Event> event = new ArrayList<Event>();
    @JsonProperty("auth")
    private Object auth;
    /**
     * Protocol Profile Behavior
     * <p>
     * Set of configurations used to alter the usual behavior of sending the request
     * 
     */
    @JsonProperty("protocolProfileBehavior")
    @JsonPropertyDescription("Set of configurations used to alter the usual behavior of sending the request")
    private ProtocolProfileBehavior protocolProfileBehavior;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * A folder's friendly name is defined by this field. You would want to set this field to a value that would allow you to easily identify this folder.
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * A folder's friendly name is defined by this field. You would want to set this field to a value that would allow you to easily identify this folder.
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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

    /**
     * Variable List
     * <p>
     * Collection variables allow you to define a set of variables, that are a *part of the collection*, as opposed to environments, which are separate entities.
     * *Note: Collection variables must not contain any sensitive information.*
     * 
     */
    @JsonProperty("variable")
    public List<Variable> getVariable() {
        return variable;
    }

    /**
     * Variable List
     * <p>
     * Collection variables allow you to define a set of variables, that are a *part of the collection*, as opposed to environments, which are separate entities.
     * *Note: Collection variables must not contain any sensitive information.*
     * 
     */
    @JsonProperty("variable")
    public void setVariable(List<Variable> variable) {
        this.variable = variable;
    }

    /**
     * Items are entities which contain an actual HTTP request, and sample responses attached to it. Folders may contain many items.
     * (Required)
     * 
     */
    @JsonProperty("item")
    public List<Object> getItem() {
        return item;
    }

    /**
     * Items are entities which contain an actual HTTP request, and sample responses attached to it. Folders may contain many items.
     * (Required)
     * 
     */
    @JsonProperty("item")
    public void setItem(List<Object> item) {
        this.item = item;
    }

    /**
     * Event List
     * <p>
     * Postman allows you to configure scripts to run when specific events occur. These scripts are stored here, and can be referenced in the collection by their ID.
     * 
     */
    @JsonProperty("event")
    public List<Event> getEvent() {
        return event;
    }

    /**
     * Event List
     * <p>
     * Postman allows you to configure scripts to run when specific events occur. These scripts are stored here, and can be referenced in the collection by their ID.
     * 
     */
    @JsonProperty("event")
    public void setEvent(List<Event> event) {
        this.event = event;
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
     * Protocol Profile Behavior
     * <p>
     * Set of configurations used to alter the usual behavior of sending the request
     * 
     */
    @JsonProperty("protocolProfileBehavior")
    public ProtocolProfileBehavior getProtocolProfileBehavior() {
        return protocolProfileBehavior;
    }

    /**
     * Protocol Profile Behavior
     * <p>
     * Set of configurations used to alter the usual behavior of sending the request
     * 
     */
    @JsonProperty("protocolProfileBehavior")
    public void setProtocolProfileBehavior(ProtocolProfileBehavior protocolProfileBehavior) {
        this.protocolProfileBehavior = protocolProfileBehavior;
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
        sb.append(ItemGroup.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("variable");
        sb.append('=');
        sb.append(((this.variable == null)?"<null>":this.variable));
        sb.append(',');
        sb.append("item");
        sb.append('=');
        sb.append(((this.item == null)?"<null>":this.item));
        sb.append(',');
        sb.append("event");
        sb.append('=');
        sb.append(((this.event == null)?"<null>":this.event));
        sb.append(',');
        sb.append("auth");
        sb.append('=');
        sb.append(((this.auth == null)?"<null>":this.auth));
        sb.append(',');
        sb.append("protocolProfileBehavior");
        sb.append('=');
        sb.append(((this.protocolProfileBehavior == null)?"<null>":this.protocolProfileBehavior));
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
        result = ((result* 31)+((this.protocolProfileBehavior == null)? 0 :this.protocolProfileBehavior.hashCode()));
        result = ((result* 31)+((this.item == null)? 0 :this.item.hashCode()));
        result = ((result* 31)+((this.auth == null)? 0 :this.auth.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.variable == null)? 0 :this.variable.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.event == null)? 0 :this.event.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ItemGroup) == false) {
            return false;
        }
        ItemGroup rhs = ((ItemGroup) other);
        return (((((((((this.protocolProfileBehavior == rhs.protocolProfileBehavior)||((this.protocolProfileBehavior!= null)&&this.protocolProfileBehavior.equals(rhs.protocolProfileBehavior)))&&((this.item == rhs.item)||((this.item!= null)&&this.item.equals(rhs.item))))&&((this.auth == rhs.auth)||((this.auth!= null)&&this.auth.equals(rhs.auth))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.variable == rhs.variable)||((this.variable!= null)&&this.variable.equals(rhs.variable))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.event == rhs.event)||((this.event!= null)&&this.event.equals(rhs.event))));
    }

}
