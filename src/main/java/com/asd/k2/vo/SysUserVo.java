package com.asd.k2.vo;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "系统用户返回对象")
public record SysUserVo(
		@Schema(description = "主键 ID", example = "1") Long id,
		@Schema(description = "用户名", example = "zhangsan") String username,
		@Schema(description = "昵称", example = "张三") String nickname,
		@Schema(description = "邮箱") String email,
		@Schema(description = "头像URL") String avatar,
		@Schema(description = "手机号") String phone,
		@Schema(description = "状态：1正常 0禁用", example = "1") Integer status,
		@Schema(description = "最后登录时间") LocalDateTime lastLoginTime,
		@Schema(description = "创建时间") LocalDateTime createdAt,
		@Schema(description = "更新时间") LocalDateTime updatedAt) {
}
