package com.ssafy.herehear.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LibraryGetResponse")
public class LibraryGetRes {
	@ApiModelProperty(name="å ID")
	private Long id;
	
	@ApiModelProperty(name="���� ����", example = "��:0, ��:1, ��:2")
	private int read_status;
	
	@ApiModelProperty(name="����")
	private int stars;
}
