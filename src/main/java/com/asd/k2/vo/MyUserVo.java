package com.asd.k2.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户返回对象")
public record MyUserVo(
		@Schema(description = "主键 ID", example = "1") Integer id,
		@Schema(description = "姓名", example = "张三") String name,
		@Schema(description = "别名", example = "zhangsan") String asName) {
}
