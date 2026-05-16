-- my_user 示例数据（主键已存在则跳过，可重复启动）
INSERT IGNORE INTO my_user (id, name, as_name, birthday, sex, email, address, age) VALUES
(1, '张三', 'zhangsan', '1990-05-20', 'M', 'zhangsan@example.com', '北京市朝阳区', 35),
(2, '李四', 'lisi', '1992-08-15', 'M', 'lisi@example.com', '上海市浦东新区', 33),
(3, '王五', 'wangwu', '1988-12-01', 'M', 'wangwu@example.com', '广州市天河区', 37),
(4, '赵六', 'zhaoliu', '1995-03-18', 'F', 'zhaoliu@example.com', '深圳市南山区', 30),
(5, '小明', 'xiaoming', '2000-01-10', 'M', 'xiaoming@example.com', '杭州市西湖区', 25);
