package com.freebee.hive.dto.institute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituteResponse extends InstituteBase {
    @JsonProperty("id")
    private String institutionID;

    @JsonProperty("joined_on")
    private String joinedOn;

    @JsonProperty("added_by")
    private String addedBy;

    @JsonProperty("location_id")
    private String locationID;

    @JsonProperty("assigned_kam")
    private String assignedKam;
}
