-- DROP DATABASE gzjk_finance;

-- 创建数据库
CREATE DATABASE gzjk_finance;

USE gzjk_finance;

-- 创建部门（用户依赖）
CREATE TABLE t_department
(
  id     INT AUTO_INCREMENT,
  `name` VARCHAR(30) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

-- 创建用户表
CREATE TABLE t_user
(
  id           INT                         AUTO_INCREMENT,
  `name`       VARCHAR(30) UNIQUE NOT NULL,
  `password`   VARCHAR(15)        NOT NULL,
  real_name    VARCHAR(20), -- 姓名
  gender       INT, -- 0 male 1 female
  mail         VARCHAR(50),
  phone_number VARCHAR(20),
  dept_id      INT, -- 部门
  create_time  TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (dept_id) REFERENCES t_department (id)
);

-- 新增partmanet manager字段 并外键
ALTER TABLE t_department
  ADD COLUMN manager INT;
ALTER TABLE t_department
  ADD CONSTRAINT dept_manager_id FOREIGN KEY (manager) REFERENCES t_user (id);

-- 初始化部门数据
INSERT INTO t_department
VALUES (NULL, "计划统计部", NULL);
INSERT INTO t_department
VALUES (NULL, "财务部", NULL);
INSERT INTO t_department
VALUES (NULL, "其他", NULL);

-- 创建管理员账号
INSERT INTO t_user
VALUES (NULL, "root", "12345678", "管理员", 0, NULL, NULL, 1, NULL);

-- 创建商业实体表, 主项目的业主单位，合同甲乙丙，支付记录的来往都必须先在此表注册
CREATE TABLE t_entity
(
  id           INT                         AUTO_INCREMENT,
  `name`       VARCHAR(30) UNIQUE NOT NULL, -- 机构名称
  credit_code  VARCHAR(30) UNIQUE, -- 统一机构信用代码
  adress       VARCHAR(30),
  bank_account VARCHAR(30), -- 银行账号，付款录入需要用到
  note         VARCHAR(100), -- 备注
  is_internal  BOOL, -- 是否为内部单位
  create_by    INT,
  create_time  TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (create_by) REFERENCES t_user (id)
);

-- 创建项目表
-- 主项目，不直接关联合同（后置约束）
CREATE TABLE t_main_project
(
  id          INT                         AUTO_INCREMENT,
  number      VARCHAR(20) UNIQUE, -- 编号,识别序号
  `name`      VARCHAR(20) UNIQUE NOT NULL,
  `owner`     INT,
  note        VARCHAR(100),
  create_by   INT,
  create_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (create_by) REFERENCES t_user (id),
  FOREIGN KEY (`owner`) REFERENCES t_entity (id)
);
-- 子项目， 可关联多个合同（后置约束）
CREATE TABLE t_sub_project
(
  id          INT                         AUTO_INCREMENT,
  number      VARCHAR(20) UNIQUE, -- 编号,识别序号
  `name`      VARCHAR(20) UNIQUE NOT NULL,
  main_pid    INT, -- 主项目id
  note        VARCHAR(100),
  create_by   INT,
  create_time TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (create_by) REFERENCES t_user (id),
  FOREIGN KEY (main_pid) REFERENCES t_main_project (id)
);

-- 合同
-- 合同类型
CREATE TABLE t_contract_type
(
  `type` VARCHAR(10), -- 监理，施工等等
  abbr   VARCHAR(10) UNICODE NOT NULL,
  PRIMARY KEY (`type`)
);
-- ALTER TABLE t_contract_type ADD INDEX index_type(`type`);

INSERT INTO t_contract_type (`type`, abbr)
VALUES ("可研编制", "KY");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("见证", "JZ");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("环评", "HP");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("设计", "SJ");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("图审", "TS");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("招标代理", "ZB");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("测量放线", "CL");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("勘察", "KC");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("平场", "PC");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("监理", "JL");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("施工", "SG");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("检测", "JC");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("配套", "PT");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("水电气", "SD");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("融资", "RZ");
INSERT INTO t_contract_type (`type`, abbr)
VALUES ("其它", "QT");

-- 合同
CREATE TABLE t_contract
(
  id                   INT                          AUTO_INCREMENT,
  number               VARCHAR(20) UNICODE NOT NULL, -- 编号
  `type`               VARCHAR(10), -- 合同类别
  price                INT, -- 中标价，单位元
  pay_rate             INT, -- 竣工验收付款比例，百分数
  done_pay_rate        INT, -- 竣工验收付款比例，百分数
  state                INT, -- 状态：0 开工 1 完工 2 竣工验收 3 结算 4 出质保期
  approval_sum         INT, -- 累计审批
  pay_sum              INT, -- 累计支付
  bill_sum             INT, -- 累计发票
  quality_deposit_rate INT, -- 质保金比例, 百分数
  quality_period       INT, --  质保期，单位天
  completion_date      DATE, -- 实际竣工日期
  quality_fix_pay      INT, -- 质保期维修费用
  audit_price          INT, -- 审计结算价
  final_audit_price    INT, -- 二次抽审价格
  project_id           INT                 NOT NULL, -- 关联的子项目id
  party_a              INT                 NOT NULL, -- 甲方 关联t_business_entity
  party_b              INT                 NOT NULL, -- 乙方
  sign_date            DATE, -- 签署日期
  note                 VARCHAR(100), -- 备注
  create_time          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 合同记录创建时间
  create_by            INT,
  PRIMARY KEY (id),
  FOREIGN KEY (project_id) REFERENCES t_sub_project (id),
  FOREIGN KEY (party_a) REFERENCES t_entity (id),
  FOREIGN KEY (party_b) REFERENCES t_entity (id),
  FOREIGN KEY (`type`) REFERENCES t_contract_type (`type`),
  FOREIGN KEY (create_by) REFERENCES t_user (id)
);

-- drop table t_contract;

CREATE TABLE t_contract
(
  id                   INT                          AUTO_INCREMENT,
  number               VARCHAR(20) UNICODE NOT NULL, -- 编号
  `type`               VARCHAR(10), -- 合同类别
  price                INT, -- 中标价，单位元
  pay_rate             INT, -- 竣工验收付款比例，百分数
  done_pay_rate        INT, -- 竣工验收付款比例，百分数
  state                INT, -- 状态：0 开工 1 完工 2 竣工验收 3 结算 4 出质保期
  approval_sum         INT, -- 累计审`type`批
  pay_sum              INT, -- 累计支付
  bill_sum             INT, -- 累计发票
  quality_deposit_rate INT, -- 质保金比例, 百分数
  quality_period       INT, --  质保期，单位天
  completion_date      DATE, -- 实际竣工日期
  quality_fix_pay      INT, -- 质保期维修费用
  audit_price          INT, -- 审计结算价
  final_audit_price    INT, -- 二次抽审价格
  project_id           INT                 NOT NULL, -- 关联的子项目id
  party_a              INT                 NOT NULL, -- 甲方 关联t_business_entity
  party_b              INT                 NOT NULL, -- 乙方
  sign_date            DATE, -- 签署日期
  note                 VARCHAR(100), -- 备注
  create_time          TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 合同记录创建时间
  create_by            INT,
  PRIMARY KEY (id)
);

-- ALTER TABLE t_contract ADD INDEX index_type(`type`);
ALTER TABLE t_contract
  ADD FOREIGN KEY (project_id) REFERENCES t_sub_project (id);
ALTER TABLE t_contract
  ADD FOREIGN KEY (party_a) REFERENCES t_entity (id);
ALTER TABLE t_contract
  ADD FOREIGN KEY (party_b) REFERENCES t_entity (id);
ALTER TABLE t_contract
  ADD FOREIGN KEY (`type`) REFERENCES t_contract_type (`type`);
ALTER TABLE t_contract
  ADD FOREIGN KEY (create_by) REFERENCES t_user (id);

-- 财政审批
CREATE TABLE t_financial_approval
(
  id          INT                AUTO_INCREMENT,
  number      VARCHAR(30) UNIQUE, -- 编号，财政审批令编号
  amount      INT, -- 审批金额，单位元
  `date`      DATE, -- 审批时间
  contract_id INT, -- 关联的合同
  note        VARCHAR(30),
  create_by   INT, -- 创建者·
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (create_by) REFERENCES t_user (id),
  FOREIGN KEY (contract_id) REFERENCES t_contract (id)
);

-- 付款记录
CREATE TABLE t_payment
(
  id            INT                AUTO_INCREMENT,
  number        VARCHAR(30) UNIQUE, -- 凭证号，财务系统内编号
  amount        INT, -- 支付金额，单位元
  should_amount INT, -- 应付金额
  cut_amount    INT, -- 扣款金额
  bill_amount   INT, -- 发票金额
  cut_note      VARCHAR(30), -- 扣款说明
  trade_time    DATETIME, -- 交易时间
  `from`        INT, -- 付款方
  `to`          INT, -- 收款方
  from_account  VARCHAR(30), -- 付款账号, entity中的账号只是用与提示关联
  to_account    VARCHAR(30), -- 收款账号
  from_bank     VARCHAR(20),
  to_bank       VARCHAR(20),
  contract_id   INT, -- 关联合同id
  create_by     INT, -- 创建者·
  create_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (create_by) REFERENCES t_user (id),
  FOREIGN KEY (contract_id) REFERENCES t_contract (id),
  FOREIGN KEY (`from`) REFERENCES t_entity (id),
  FOREIGN KEY (`to`) REFERENCES t_entity (id)
);