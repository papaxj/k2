package com.asd.k2.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户返回对象")
public record MyUserVo(
		@Schema(description = "主键 ID", example = "1") Integer id,
		@Schema(description = "名称", example = "张三") String name,
		@Schema(description = "别名", example = "zhangsan") String asName,
		@Schema(description = "生日", example = "1990-05-20") String birthday,
		@Schema(description = "性别：M-男，F-女", example = "M") String sex,
		@Schema(description = "电子邮件", example = "zhangsan@example.com") String email,
		@Schema(description = "住址", example = "北京市朝阳区") String address,
		@Schema(description = "年龄", example = "30") Integer age) {
}
