package com.ssafy.herehear.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BookGetResponse")
public class BookGetRes {
	
	@ApiModelProperty(name="å ID")
	private Long id;
	
	@ApiModelProperty(name="å ����")
	private String title;
	
	@ApiModelProperty(name="å ǥ��")
	private String img_url;
	
	@ApiModelProperty(name="å �Ұ�")
	private String description;	
	
	@ApiModelProperty(name="���� �� ��")
	private int stars_sum;
	
	@ApiModelProperty(name="���� ����")
	private int stars_count;

}
