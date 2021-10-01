package com.ssafy.herehear.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BookSearchGetResponse")
public class BookSearchGetRes {
	
	@ApiModelProperty(name="å ID")
	private Long id;
	
	@ApiModelProperty(name="å ����")
	private String title;
	
	@ApiModelProperty(name="å ǥ��")
	private String img_url;
	
}
