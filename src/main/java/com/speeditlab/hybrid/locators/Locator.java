package com.speeditlab.hybrid.locators;

/**
 * Created by kugajjar on 2/8/15.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
                           "rows"
                   })
public class Locator
{

    @JsonProperty("rows")
    private List<Row> rows = new ArrayList<Row>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The rows
     */
    @JsonProperty("rows")
    public List<Row> getRows()
    {
        return rows;
    }

    /**
     * @param rows The rows
     */
    @JsonProperty("rows")
    public void setRows(List<Row> rows)
    {
        this.rows = rows;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
