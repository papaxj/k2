DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS offer_info;
DROP TABLE IF EXISTS interview_question;
DROP TABLE IF EXISTS interview_round;
DROP TABLE IF EXISTS job_application;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) DEFAULT NULL COMMENT '昵称',
    email VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    avatar VARCHAR(255) DEFAULT NULL COMMENT '头像',
    phone VARCHAR(30) DEFAULT NULL COMMENT '手机号',
    status TINYINT DEFAULT 1 COMMENT '状态 1正常 0禁用',
    last_login_time DATETIME DEFAULT NULL COMMENT '最后登录时间',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除'
) COMMENT='系统用户表';

CREATE TABLE company (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    name VARCHAR(100) NOT NULL COMMENT '公司名称',
    industry VARCHAR(100) DEFAULT NULL COMMENT '行业',
    website VARCHAR(255) DEFAULT NULL COMMENT '官网',
    city VARCHAR(100) DEFAULT NULL COMMENT '城市',
    company_size VARCHAR(50) DEFAULT NULL COMMENT '公司规模',
    financing_stage VARCHAR(50) DEFAULT NULL COMMENT '融资阶段',
    address VARCHAR(255) DEFAULT NULL COMMENT '公司地址',
    hr_name VARCHAR(100) DEFAULT NULL COMMENT 'HR姓名',
    hr_contact VARCHAR(100) DEFAULT NULL COMMENT 'HR联系方式',
    remark TEXT COMMENT '备注',
    status TINYINT DEFAULT 1 COMMENT '状态',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_user_id(user_id),
    INDEX idx_company_name(name)
) COMMENT='公司信息表';

CREATE TABLE job_application (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    company_id BIGINT NOT NULL COMMENT '公司ID',
    position_name VARCHAR(100) NOT NULL COMMENT '岗位名称',
    department VARCHAR(100) DEFAULT NULL COMMENT '部门',
    employment_type VARCHAR(50) DEFAULT NULL COMMENT '全职/实习/远程',
    work_city VARCHAR(100) DEFAULT NULL COMMENT '工作城市',
    salary_min INT DEFAULT NULL COMMENT '最低薪资',
    salary_max INT DEFAULT NULL COMMENT '最高薪资',
    salary_months INT DEFAULT 12 COMMENT '薪资月数',
    job_desc TEXT COMMENT '职位描述',
    source VARCHAR(50) DEFAULT NULL COMMENT '来源 Boss/猎聘/内推',
    source_link VARCHAR(500) DEFAULT NULL COMMENT '职位链接',
    apply_date DATE DEFAULT NULL COMMENT '投递日期',
    current_stage VARCHAR(50) DEFAULT 'APPLIED' COMMENT '当前阶段',
    status VARCHAR(50) DEFAULT 'PROCESSING' COMMENT '状态',
    priority_level TINYINT DEFAULT 2 COMMENT '优先级 1低 2中 3高',
    expected_salary INT DEFAULT NULL COMMENT '期望薪资',
    remark TEXT COMMENT '备注',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_user_id(user_id),
    INDEX idx_company_id(company_id),
    INDEX idx_status(status),
    INDEX idx_apply_date(apply_date)
) COMMENT='岗位申请表';

CREATE TABLE interview_round (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    application_id BIGINT NOT NULL COMMENT '申请ID',
    round_no INT NOT NULL COMMENT '轮次',
    round_type VARCHAR(50) DEFAULT NULL COMMENT 'HR/TECH/LEADER/CEO',
    interviewer VARCHAR(100) DEFAULT NULL COMMENT '面试官',
    interviewer_title VARCHAR(100) DEFAULT NULL COMMENT '面试官职位',
    interview_method VARCHAR(50) DEFAULT NULL COMMENT '现场/电话/视频',
    meeting_link VARCHAR(500) DEFAULT NULL COMMENT '会议链接',
    interview_time DATETIME DEFAULT NULL COMMENT '面试时间',
    duration_minutes INT DEFAULT NULL COMMENT '时长',
    result VARCHAR(50) DEFAULT 'PENDING' COMMENT '结果',
    score DECIMAL(5,2) DEFAULT NULL COMMENT '评分',
    summary TEXT COMMENT '面试总结',
    feedback TEXT COMMENT '反馈',
    next_round_time DATETIME DEFAULT NULL COMMENT '下一轮时间',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_application_id(application_id),
    INDEX idx_interview_time(interview_time),
    INDEX idx_result(result)
) COMMENT='面试轮次表';

CREATE TABLE interview_question (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    round_id BIGINT NOT NULL COMMENT '轮次ID',
    category VARCHAR(50) DEFAULT NULL COMMENT '分类 Java/MySQL/Redis',
    question TEXT NOT NULL COMMENT '问题',
    my_answer TEXT COMMENT '我的回答',
    correct_answer TEXT COMMENT '参考答案',
    difficulty_level TINYINT DEFAULT 2 COMMENT '难度等级',
    is_answered_correctly TINYINT DEFAULT 0 COMMENT '是否答对',
    remark TEXT COMMENT '备注',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_round_id(round_id),
    INDEX idx_category(category)
) COMMENT='面试问题表';

CREATE TABLE offer_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    application_id BIGINT NOT NULL COMMENT '申请ID',
    base_salary INT DEFAULT NULL COMMENT '基础薪资',
    bonus_salary INT DEFAULT NULL COMMENT '奖金',
    stock_value INT DEFAULT NULL COMMENT '股票价值',
    sign_bonus INT DEFAULT NULL COMMENT '签字费',
    other_benefits TEXT COMMENT '其他福利',
    offer_date DATE DEFAULT NULL COMMENT 'Offer日期',
    deadline_date DATE DEFAULT NULL COMMENT '截止日期',
    join_date DATE DEFAULT NULL COMMENT '入职日期',
    status VARCHAR(50) DEFAULT 'PENDING' COMMENT '状态',
    remark TEXT COMMENT '备注',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    UNIQUE KEY uk_application_id(application_id),
    INDEX idx_status(status)
) COMMENT='Offer信息表';

CREATE TABLE attachment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    biz_type VARCHAR(50) NOT NULL COMMENT '业务类型',
    biz_id BIGINT NOT NULL COMMENT '业务ID',
    file_name VARCHAR(255) NOT NULL COMMENT '文件名',
    file_url VARCHAR(500) NOT NULL COMMENT '文件URL',
    file_size BIGINT DEFAULT NULL COMMENT '文件大小',
    file_type VARCHAR(100) DEFAULT NULL COMMENT '文件类型',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_user_id(user_id),
    INDEX idx_biz(biz_type, biz_id)
) COMMENT='附件表';
