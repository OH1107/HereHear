package com.ssafy.herehear.api.response;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentGetResponse")
public class CommentGetRes {
	
	@ApiModelProperty(name = "������ ID")
	private Long id;
	
	@ApiModelProperty(name = "���� ID")
	private Long userId;
	
	@ApiModelProperty(name = "å ID")
	private Long bookId;
	
	@ApiModelProperty(name = "������ ����")
	private String content;
	
	@ApiModelProperty(name = "���� �ð�")
	private Double reading_time;
	
	@ApiModelProperty(name = "���� ��¥")
	private Date date;
	
	@ApiModelProperty(name = "��� ���� ����", example = "true: ����, false: �����")
	private Boolean isshow;
	
}
