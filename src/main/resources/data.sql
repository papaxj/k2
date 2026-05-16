-- my_user 示例数据（主键已存在则跳过，可重复启动）
INSERT IGNORE INTO my_user (id, name, as_name) VALUES
(1, '张三', 'zhangsan'),
(2, '李四', 'lisi'),
(3, '王五', 'wangwu'),
(4, '赵六', 'zhaoliu'),
(5, '小明', 'xiaoming');
