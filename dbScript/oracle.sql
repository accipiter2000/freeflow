prompt
prompt Creating table FF_PROC_DEF
prompt ==========================
prompt
create table FF_PROC_DEF
(
  PROC_DEF_ID_                  VARCHAR2(40) not null,
  PROC_DEF_CODE_                VARCHAR2(60) not null,
  PROC_DEF_NAME_                VARCHAR2(60) not null,
  PROC_DEF_CAT_                 VARCHAR2(100),
  PROC_DEF_MODEL_               CLOB not null,
  PROC_DEF_DIAGRAM_FILE_        BLOB,
  PROC_DEF_DIAGRAM_FILE_NAME_   VARCHAR2(300),
  PROC_DEF_DIAGRAM_FILE_LENGTH_ INTEGER,
  PROC_DEF_DIAGRAM_WIDTH_       INTEGER,
  PROC_DEF_DIAGRAM_HEIGHT_      INTEGER,
  MEMO_                         VARCHAR2(300),
  EXT_ATTR_1_                   VARCHAR2(120),
  EXT_ATTR_2_                   VARCHAR2(120),
  EXT_ATTR_3_                   VARCHAR2(120),
  EXT_ATTR_4_                   VARCHAR2(120),
  EXT_ATTR_5_                   VARCHAR2(120),
  EXT_ATTR_6_                   VARCHAR2(120),
  EXT_ATTR_7_                   VARCHAR2(120),
  EXT_ATTR_8_                   VARCHAR2(120),
  VERSION_                      INTEGER not null,
  PROC_DEF_STATUS_              VARCHAR2(20) not null,
  CREATION_DATE_                TIMESTAMP(6),
  UPDATE_DATE_                  TIMESTAMP(6),
  OPERATOR_ID_                  VARCHAR2(40),
  OPERATOR_NAME_                VARCHAR2(60)
)
;
comment on table FF_PROC_DEF
  is '流程定义';
comment on column FF_PROC_DEF.PROC_DEF_ID_
  is '流程定义ID';
comment on column FF_PROC_DEF.PROC_DEF_CODE_
  is '流程定义编码';
comment on column FF_PROC_DEF.PROC_DEF_NAME_
  is '流程定义名称';
comment on column FF_PROC_DEF.PROC_DEF_CAT_
  is '流程定义分类';
comment on column FF_PROC_DEF.PROC_DEF_MODEL_
  is '流程定义模型';
comment on column FF_PROC_DEF.PROC_DEF_DIAGRAM_FILE_
  is '流程定义图文件';
comment on column FF_PROC_DEF.PROC_DEF_DIAGRAM_FILE_NAME_
  is '流程定义图文件名称';
comment on column FF_PROC_DEF.PROC_DEF_DIAGRAM_FILE_LENGTH_
  is '流程定义图文件长度';
comment on column FF_PROC_DEF.PROC_DEF_DIAGRAM_WIDTH_
  is '流程定义图宽度';
comment on column FF_PROC_DEF.PROC_DEF_DIAGRAM_HEIGHT_
  is '流程定义图高度';
comment on column FF_PROC_DEF.MEMO_
  is '备注';
comment on column FF_PROC_DEF.EXT_ATTR_1_
  is '扩展属性1';
comment on column FF_PROC_DEF.EXT_ATTR_2_
  is '扩展属性2';
comment on column FF_PROC_DEF.EXT_ATTR_3_
  is '扩展属性3';
comment on column FF_PROC_DEF.EXT_ATTR_4_
  is '扩展属性4';
comment on column FF_PROC_DEF.EXT_ATTR_5_
  is '扩展属性5';
comment on column FF_PROC_DEF.EXT_ATTR_6_
  is '扩展属性6';
comment on column FF_PROC_DEF.EXT_ATTR_7_
  is '扩展属性7';
comment on column FF_PROC_DEF.EXT_ATTR_8_
  is '扩展属性8';
comment on column FF_PROC_DEF.VERSION_
  is '版本';
comment on column FF_PROC_DEF.PROC_DEF_STATUS_
  is '流程定义状态';
comment on column FF_PROC_DEF.CREATION_DATE_
  is '创建日期';
comment on column FF_PROC_DEF.UPDATE_DATE_
  is '更新日期';
comment on column FF_PROC_DEF.OPERATOR_ID_
  is '操作人员ID';
comment on column FF_PROC_DEF.OPERATOR_NAME_
  is '操作人员名称';
alter table FF_PROC_DEF
  add constraint PK_FF_PROC_DEF primary key (PROC_DEF_ID_);
alter table FF_PROC_DEF
  add constraint UQ_FF_PROC_DEF unique (PROC_DEF_CODE_, VERSION_);

prompt
prompt Creating table FF_ADJUST_PROC_DEF
prompt =================================
prompt
create table FF_ADJUST_PROC_DEF
(
  ADJUST_PROC_DEF_ID_           VARCHAR2(40) not null,
  PROC_DEF_ID_                  VARCHAR2(40) not null,
  PROC_DEF_MODEL_               CLOB not null,
  PROC_DEF_DIAGRAM_FILE_        BLOB,
  PROC_DEF_DIAGRAM_FILE_NAME_   VARCHAR2(300),
  PROC_DEF_DIAGRAM_FILE_LENGTH_ INTEGER,
  PROC_DEF_DIAGRAM_WIDTH_       INTEGER,
  PROC_DEF_DIAGRAM_HEIGHT_      INTEGER,
  CREATION_DATE_                TIMESTAMP(6),
  UPDATE_DATE_                  TIMESTAMP(6),
  OPERATOR_ID_                  VARCHAR2(40),
  OPERATOR_NAME_                VARCHAR2(60)
)
;
comment on table FF_ADJUST_PROC_DEF
  is '调整流程定义';
comment on column FF_ADJUST_PROC_DEF.ADJUST_PROC_DEF_ID_
  is '调整流程定义ID';
comment on column FF_ADJUST_PROC_DEF.PROC_DEF_ID_
  is '流程定义ID';
comment on column FF_ADJUST_PROC_DEF.PROC_DEF_MODEL_
  is '流程定义模型';
comment on column FF_ADJUST_PROC_DEF.PROC_DEF_DIAGRAM_FILE_
  is '流程定义图文件';
comment on column FF_ADJUST_PROC_DEF.PROC_DEF_DIAGRAM_FILE_NAME_
  is '流程定义图文件名称';
comment on column FF_ADJUST_PROC_DEF.PROC_DEF_DIAGRAM_FILE_LENGTH_
  is '流程定义图文件长度';
comment on column FF_ADJUST_PROC_DEF.PROC_DEF_DIAGRAM_WIDTH_
  is '流程定义图宽度';
comment on column FF_ADJUST_PROC_DEF.PROC_DEF_DIAGRAM_HEIGHT_
  is '流程定义图高度';
comment on column FF_ADJUST_PROC_DEF.CREATION_DATE_
  is '创建日期';
comment on column FF_ADJUST_PROC_DEF.UPDATE_DATE_
  is '更新日期';
comment on column FF_ADJUST_PROC_DEF.OPERATOR_ID_
  is '操作人员ID';
comment on column FF_ADJUST_PROC_DEF.OPERATOR_NAME_
  is '操作人员名称';
alter table FF_ADJUST_PROC_DEF
  add constraint PK_FF_ADJUST_PROC_DEF primary key (ADJUST_PROC_DEF_ID_);
alter table FF_ADJUST_PROC_DEF
  add constraint FK_FF_ADJUST_PROC_DEF_PROC_DEF foreign key (PROC_DEF_ID_)
  references FF_PROC_DEF (PROC_DEF_ID_);

prompt
prompt Creating table FF_DELEGATE
prompt ==========================
prompt
create table FF_DELEGATE
(
  DELEGATE_ID_    VARCHAR2(40) not null,
  ASSIGNEE_       VARCHAR2(60),
  ASSIGNEE_NAME_  VARCHAR2(60),
  DELEGATOR_      VARCHAR2(60) not null,
  DELEGATOR_NAME_ VARCHAR2(60),
  START_DATE_     TIMESTAMP(6),
  END_DATE_       TIMESTAMP(6)
)
;
comment on table FF_DELEGATE
  is '代理';
comment on column FF_DELEGATE.DELEGATE_ID_
  is '代理ID';
comment on column FF_DELEGATE.ASSIGNEE_
  is '办理人';
comment on column FF_DELEGATE.ASSIGNEE_NAME_
  is '办理人名称';
comment on column FF_DELEGATE.DELEGATOR_
  is '代理人';
comment on column FF_DELEGATE.DELEGATOR_NAME_
  is '代理人名称';
comment on column FF_DELEGATE.START_DATE_
  is '开始日期';
comment on column FF_DELEGATE.END_DATE_
  is '结束日期';
alter table FF_DELEGATE
  add constraint PK_FF_DELEGATE primary key (DELEGATE_ID_);

prompt
prompt Creating table FF_PROC
prompt ======================
prompt
create table FF_PROC
(
  PROC_ID_                  VARCHAR2(40) not null,
  PROC_DEF_ID_              VARCHAR2(40) not null,
  ADJUST_PROC_DEF_ID_       VARCHAR2(40),
  ISOLATE_SUB_PROC_NODE_ID_ VARCHAR2(40),
  BIZ_ID_                   VARCHAR2(40),
  BIZ_TYPE_                 VARCHAR2(60),
  BIZ_CODE_                 VARCHAR2(100),
  BIZ_NAME_                 VARCHAR2(100),
  BIZ_DESC_                 VARCHAR2(300),
  PROC_START_USER_          VARCHAR2(40),
  PROC_START_USER_NAME_     VARCHAR2(60),
  PROC_END_USER_            VARCHAR2(40),
  PROC_END_USER_NAME_       VARCHAR2(60),
  PROC_END_DATE_            TIMESTAMP(6),
  PROC_STATUS_              VARCHAR2(20) not null,
  CREATION_DATE_            TIMESTAMP(6) not null
)
;
comment on table FF_PROC
  is '流程';
comment on column FF_PROC.PROC_ID_
  is '流程ID';
comment on column FF_PROC.PROC_DEF_ID_
  is '流程定义ID';
comment on column FF_PROC.ADJUST_PROC_DEF_ID_
  is '调整流程定义ID';
comment on column FF_PROC.ISOLATE_SUB_PROC_NODE_ID_
  is '独立子流程所属节点ID';
comment on column FF_PROC.BIZ_ID_
  is '业务主键';
comment on column FF_PROC.BIZ_TYPE_
  is '业务类型';
comment on column FF_PROC.BIZ_CODE_
  is '业务编码';
comment on column FF_PROC.BIZ_NAME_
  is '业务名称';
comment on column FF_PROC.BIZ_DESC_
  is '业务备注';
comment on column FF_PROC.PROC_START_USER_
  is '流程开始人员';
comment on column FF_PROC.PROC_START_USER_NAME_
  is '流程开始人员名称';
comment on column FF_PROC.PROC_END_USER_
  is '流程完成人员';
comment on column FF_PROC.PROC_END_USER_NAME_
  is '流程完成人员名称';
comment on column FF_PROC.PROC_END_DATE_
  is '流程完成日期';
comment on column FF_PROC.PROC_STATUS_
  is '流程状态';
comment on column FF_PROC.CREATION_DATE_
  is '创建日期';
alter table FF_PROC
  add constraint PK_FF_PROC primary key (PROC_ID_);
alter table FF_PROC
  add constraint FK_FF_PROC_PROC_DEF foreign key (PROC_DEF_ID_)
  references FF_PROC_DEF (PROC_DEF_ID_);
create index FK_PROC_DEF on FF_PROC (PROC_DEF_ID_);

prompt
prompt Creating table FF_NODE
prompt ======================
prompt
create table FF_NODE
(
  NODE_ID_                      VARCHAR2(40) not null,
  PARENT_NODE_ID_               VARCHAR2(40),
  PROC_ID_                      VARCHAR2(40) not null,
  PREVIOUS_NODE_IDS_            VARCHAR2(280),
  LAST_COMPLETE_NODE_IDS_       VARCHAR2(280),
  SUB_PROC_DEF_ID_              VARCHAR2(40),
  ADJUST_SUB_PROC_DEF_ID_       VARCHAR2(40),
  NODE_TYPE_                    VARCHAR2(20) not null,
  NODE_CODE_                    VARCHAR2(60),
  NODE_NAME_                    VARCHAR2(60),
  PARENT_NODE_CODE_             VARCHAR2(100),
  CANDIDATE_ASSIGNEE_           VARCHAR2(200),
  COMPLETE_EXPRESSION_          VARCHAR2(200),
  COMPLETE_RETURN_              VARCHAR2(200),
  EXCLUSIVE_                    VARCHAR2(200),
  WAITING_FOR_COMPLETE_NODE_    VARCHAR2(200),
  AUTO_COMPLETE_SAME_ASSIGNEE_  VARCHAR2(200),
  AUTO_COMPLETE_EMPTY_ASSIGNEE_ VARCHAR2(200),
  INFORM_                       VARCHAR2(200),
  ASSIGNEE_                     VARCHAR2(200),
  ACTION_                       VARCHAR2(300),
  DUE_DATE_                     VARCHAR2(200),
  CLAIM_                        VARCHAR2(200),
  FORWARDABLE_                  VARCHAR2(200),
  PRIORITY_                     VARCHAR2(200),
  NODE_END_USER_                VARCHAR2(40),
  NODE_END_USER_NAME_           VARCHAR2(60),
  NODE_END_DATE_                TIMESTAMP(6),
  NEXT_CANDIDATE_               CLOB,
  ISOLATE_SUB_PROC_DEF_CODE_    VARCHAR2(60),
  ISOLATE_SUB_PROC_CANDIDATE_   VARCHAR2(500),
  ISOLATE_SUB_PROC_STATUS_      VARCHAR2(60),
  NODE_STATUS_                  VARCHAR2(20) not null,
  CREATION_DATE_                TIMESTAMP(6) not null
)
;
comment on table FF_NODE
  is '节点';
comment on column FF_NODE.NODE_ID_
  is '节点ID';
comment on column FF_NODE.PARENT_NODE_ID_
  is '上级节点ID';
comment on column FF_NODE.PROC_ID_
  is '流程ID';
comment on column FF_NODE.PREVIOUS_NODE_IDS_
  is '前节点IDs';
comment on column FF_NODE.LAST_COMPLETE_NODE_IDS_
  is '最后完成节点IDs';
comment on column FF_NODE.SUB_PROC_DEF_ID_
  is '子流程定义ID';
comment on column FF_NODE.ADJUST_SUB_PROC_DEF_ID_
  is '调整子流程定义ID';
comment on column FF_NODE.NODE_TYPE_
  is '节点类型';
comment on column FF_NODE.NODE_CODE_
  is '节点编码';
comment on column FF_NODE.NODE_NAME_
  is '节点名称';
comment on column FF_NODE.PARENT_NODE_CODE_
  is '上级节点编码';
comment on column FF_NODE.CANDIDATE_ASSIGNEE_
  is '候选人';
comment on column FF_NODE.COMPLETE_EXPRESSION_
  is '完成表达式';
comment on column FF_NODE.COMPLETE_RETURN_
  is '完成后返回前一个节点';
comment on column FF_NODE.EXCLUSIVE_
  is '排他';
comment on column FF_NODE.WAITING_FOR_COMPLETE_NODE_
  is '等待完成节点';
comment on column FF_NODE.AUTO_COMPLETE_SAME_ASSIGNEE_
  is '自动完成相同办理人任务';
comment on column FF_NODE.AUTO_COMPLETE_EMPTY_ASSIGNEE_
  is '自动完成没有办理人节点';
comment on column FF_NODE.INFORM_
  is '通知';
comment on column FF_NODE.ASSIGNEE_
  is '办理人';
comment on column FF_NODE.ACTION_
  is '业务行为';
comment on column FF_NODE.DUE_DATE_
  is '截止日期';
comment on column FF_NODE.CLAIM_
  is '认领';
comment on column FF_NODE.FORWARDABLE_
  is '可转发';
comment on column FF_NODE.PRIORITY_
  is '优先级';
comment on column FF_NODE.NODE_END_USER_
  is '节点完成人员';
comment on column FF_NODE.NODE_END_USER_NAME_
  is '节点完成人员名称';
comment on column FF_NODE.NODE_END_DATE_
  is '节点完成日期';
comment on column FF_NODE.NEXT_CANDIDATE_
  is '下个候选人';
comment on column FF_NODE.ISOLATE_SUB_PROC_DEF_CODE_
  is '独立子流程定义编码';
comment on column FF_NODE.ISOLATE_SUB_PROC_CANDIDATE_
  is '独立子流程候选人';
comment on column FF_NODE.ISOLATE_SUB_PROC_STATUS_
  is '独立子流程状态';
comment on column FF_NODE.NODE_STATUS_
  is '节点状态';
comment on column FF_NODE.CREATION_DATE_
  is '创建日期';
alter table FF_NODE
  add constraint PK_FF_NODE primary key (NODE_ID_);
alter table FF_NODE
  add constraint FK_FF_NODE_ADJUST_PROC_DEF foreign key (ADJUST_SUB_PROC_DEF_ID_)
  references FF_ADJUST_PROC_DEF (ADJUST_PROC_DEF_ID_);
alter table FF_NODE
  add constraint FK_FF_NODE_PARENT foreign key (PARENT_NODE_ID_)
  references FF_NODE (NODE_ID_);
alter table FF_NODE
  add constraint FK_FF_NODE_PROC foreign key (PROC_ID_)
  references FF_PROC (PROC_ID_);
alter table FF_NODE
  add constraint FK_FF_NODE_PROC_DEF foreign key (SUB_PROC_DEF_ID_)
  references FF_PROC_DEF (PROC_DEF_ID_);

prompt
prompt Creating table FF_OPERATION
prompt ===========================
prompt
create table FF_OPERATION
(
  OPERATION_ID_     VARCHAR2(40) not null,
  OPERATION_        VARCHAR2(40) not null,
  PROC_ID_          VARCHAR2(40),
  NODE_ID_          VARCHAR2(40),
  TASK_ID_          VARCHAR2(40),
  MEMO_             VARCHAR2(1000),
  OPERATOR_         VARCHAR2(40),
  OPERATOR_NAME_    VARCHAR2(60),
  OPERATION_DATE_   TIMESTAMP(6) not null,
  OPERATION_STATUS_ VARCHAR2(20) not null
)
;
comment on table FF_OPERATION
  is '操作';
comment on column FF_OPERATION.OPERATION_ID_
  is '操作ID';
comment on column FF_OPERATION.OPERATION_
  is '操作';
comment on column FF_OPERATION.PROC_ID_
  is '流程ID';
comment on column FF_OPERATION.NODE_ID_
  is '节点ID';
comment on column FF_OPERATION.TASK_ID_
  is '任务ID';
comment on column FF_OPERATION.MEMO_
  is '备注';
comment on column FF_OPERATION.OPERATOR_
  is '操作人';
comment on column FF_OPERATION.OPERATOR_NAME_
  is '操作人名称';
comment on column FF_OPERATION.OPERATION_DATE_
  is '操作日期';
comment on column FF_OPERATION.OPERATION_STATUS_
  is '操作状态';
alter table FF_OPERATION
  add constraint FF_FF_OPERATION primary key (OPERATION_ID_);
create index IX_FF_OPERATION_OPERATOR on FF_OPERATION (OPERATOR_);
create index IX_FF_OPERATION_PROC on FF_OPERATION (PROC_ID_);

prompt
prompt Creating table FF_NODE_OP
prompt =========================
prompt
create table FF_NODE_OP
(
  NODE_OP_ID_                   VARCHAR2(40) not null,
  OPERATION_ID_                 VARCHAR2(40) not null,
  OPERATION_TYPE_               VARCHAR2(20) not null,
  OPERATION_ORDER_              INTEGER,
  OPERATION_DATE_               TIMESTAMP(6),
  OPERATION_STATUS_             VARCHAR2(20),
  NODE_ID_                      VARCHAR2(40) not null,
  PARENT_NODE_ID_               VARCHAR2(40),
  PROC_ID_                      VARCHAR2(40),
  PREVIOUS_NODE_IDS_            VARCHAR2(280),
  LAST_COMPLETE_NODE_IDS_       VARCHAR2(280),
  SUB_PROC_DEF_ID_              VARCHAR2(40),
  ADJUST_SUB_PROC_DEF_ID_       VARCHAR2(40),
  NODE_TYPE_                    VARCHAR2(20),
  NODE_CODE_                    VARCHAR2(60),
  NODE_NAME_                    VARCHAR2(60),
  PARENT_NODE_CODE_             VARCHAR2(60),
  CANDIDATE_ASSIGNEE_           VARCHAR2(200),
  COMPLETE_EXPRESSION_          VARCHAR2(200),
  COMPLETE_RETURN_              VARCHAR2(200),
  EXCLUSIVE_                    VARCHAR2(200),
  WAITING_FOR_COMPLETE_NODE_    VARCHAR2(200),
  AUTO_COMPLETE_SAME_ASSIGNEE_  VARCHAR2(200),
  AUTO_COMPLETE_EMPTY_ASSIGNEE_ VARCHAR2(200),
  INFORM_                       VARCHAR2(200),
  ASSIGNEE_                     VARCHAR2(200),
  ACTION_                       VARCHAR2(200),
  DUE_DATE_                     VARCHAR2(200),
  CLAIM_                        VARCHAR2(200),
  FORWARDABLE_                  VARCHAR2(200),
  PRIORITY_                     VARCHAR2(200),
  NODE_END_USER_                VARCHAR2(40),
  NODE_END_USER_NAME_           VARCHAR2(60),
  NODE_END_DATE_                TIMESTAMP(6),
  NEXT_CANDIDATE_               CLOB,
  ISOLATE_SUB_PROC_DEF_CODE_    VARCHAR2(60),
  ISOLATE_SUB_PROC_CANDIDATE_   VARCHAR2(500),
  ISOLATE_SUB_PROC_STATUS_      VARCHAR2(60),
  NODE_STATUS_                  VARCHAR2(20),
  CREATION_DATE_                TIMESTAMP(6)
)
;
comment on table FF_NODE_OP
  is '节点操作';
comment on column FF_NODE_OP.NODE_OP_ID_
  is '节点操作ID';
comment on column FF_NODE_OP.OPERATION_ID_
  is '操作ID';
comment on column FF_NODE_OP.OPERATION_TYPE_
  is '操作类型';
comment on column FF_NODE_OP.OPERATION_ORDER_
  is '操作顺序';
comment on column FF_NODE_OP.OPERATION_DATE_
  is '操作日期';
comment on column FF_NODE_OP.OPERATION_STATUS_
  is '操作状态';
comment on column FF_NODE_OP.NODE_ID_
  is '节点ID';
comment on column FF_NODE_OP.PARENT_NODE_ID_
  is '上级节点ID';
comment on column FF_NODE_OP.PROC_ID_
  is '流程ID';
comment on column FF_NODE_OP.PREVIOUS_NODE_IDS_
  is '前节点IDs';
comment on column FF_NODE_OP.LAST_COMPLETE_NODE_IDS_
  is '最后完成节点IDs';
comment on column FF_NODE_OP.SUB_PROC_DEF_ID_
  is '子流程定义ID';
comment on column FF_NODE_OP.ADJUST_SUB_PROC_DEF_ID_
  is '调整子流程定义ID';
comment on column FF_NODE_OP.NODE_TYPE_
  is '节点类型';
comment on column FF_NODE_OP.NODE_CODE_
  is '节点编码';
comment on column FF_NODE_OP.NODE_NAME_
  is '节点名称';
comment on column FF_NODE_OP.PARENT_NODE_CODE_
  is '上级节点编码';
comment on column FF_NODE_OP.CANDIDATE_ASSIGNEE_
  is '候选人';
comment on column FF_NODE_OP.COMPLETE_EXPRESSION_
  is '完成表达式';
comment on column FF_NODE_OP.COMPLETE_RETURN_
  is '完成后返回前一个节点';
comment on column FF_NODE_OP.EXCLUSIVE_
  is '排他';
comment on column FF_NODE_OP.WAITING_FOR_COMPLETE_NODE_
  is '等待完成节点';
comment on column FF_NODE_OP.AUTO_COMPLETE_SAME_ASSIGNEE_
  is '自动完成相同办理人任务';
comment on column FF_NODE_OP.AUTO_COMPLETE_EMPTY_ASSIGNEE_
  is '自动完成没有办理人节点';
comment on column FF_NODE_OP.INFORM_
  is '通知';
comment on column FF_NODE_OP.ASSIGNEE_
  is '办理人';
comment on column FF_NODE_OP.ACTION_
  is '业务行为';
comment on column FF_NODE_OP.DUE_DATE_
  is '截止日期';
comment on column FF_NODE_OP.CLAIM_
  is '认领';
comment on column FF_NODE_OP.FORWARDABLE_
  is '可转发';
comment on column FF_NODE_OP.PRIORITY_
  is '优先级';
comment on column FF_NODE_OP.NODE_END_USER_
  is '节点完成人员';
comment on column FF_NODE_OP.NODE_END_USER_NAME_
  is '节点完成人员名称';
comment on column FF_NODE_OP.NODE_END_DATE_
  is '节点完成日期';
comment on column FF_NODE_OP.NEXT_CANDIDATE_
  is '下个候选人';
comment on column FF_NODE_OP.ISOLATE_SUB_PROC_DEF_CODE_
  is '独立子流程定义编码';
comment on column FF_NODE_OP.ISOLATE_SUB_PROC_CANDIDATE_
  is '独立子流程候选人';
comment on column FF_NODE_OP.ISOLATE_SUB_PROC_STATUS_
  is '独立子流程状态';
comment on column FF_NODE_OP.NODE_STATUS_
  is '节点状态';
comment on column FF_NODE_OP.CREATION_DATE_
  is '创建日期';
alter table FF_NODE_OP
  add constraint PK_FF_NODE_OP primary key (NODE_OP_ID_);
alter table FF_NODE_OP
  add constraint FK_FF_NODE_OP_OPERATION foreign key (OPERATION_ID_)
  references FF_OPERATION (OPERATION_ID_);

prompt
prompt Creating table FF_NODE_VAR
prompt ==========================
prompt
create table FF_NODE_VAR
(
  NODE_VAR_ID_   VARCHAR2(40) not null,
  NODE_ID_       VARCHAR2(40) not null,
  VAR_TYPE_      VARCHAR2(20) not null,
  VAR_NAME_      VARCHAR2(60) not null,
  VALUE_         VARCHAR2(3000),
  OBJ_           BLOB,
  CREATION_DATE_ TIMESTAMP(6) not null
)
;
comment on table FF_NODE_VAR
  is '节点变量';
comment on column FF_NODE_VAR.NODE_VAR_ID_
  is '节点变量ID';
comment on column FF_NODE_VAR.NODE_ID_
  is '节点ID';
comment on column FF_NODE_VAR.VAR_TYPE_
  is '变量类型';
comment on column FF_NODE_VAR.VAR_NAME_
  is '变量名称';
comment on column FF_NODE_VAR.VALUE_
  is '值';
comment on column FF_NODE_VAR.OBJ_
  is '对象';
comment on column FF_NODE_VAR.CREATION_DATE_
  is '创建日期';
alter table FF_NODE_VAR
  add constraint PK_FF_NODE_VAR primary key (NODE_VAR_ID_);
create index IX_SUB_PROC_VAR_NAME on FF_NODE_VAR (VAR_NAME_);
create index IX_SUB_PROC_VAR_VALUE on FF_NODE_VAR (VALUE_);

prompt
prompt Creating table FF_NODE_VAR_OP
prompt =============================
prompt
create table FF_NODE_VAR_OP
(
  NODE_VAR_OP_ID_   VARCHAR2(40) not null,
  OPERATION_ID_     VARCHAR2(40) not null,
  OPERATION_TYPE_   VARCHAR2(20) not null,
  OPERATION_ORDER_  INTEGER,
  OPERATION_DATE_   TIMESTAMP(6),
  OPERATION_STATUS_ VARCHAR2(20),
  NODE_VAR_ID_      VARCHAR2(40) not null,
  NODE_ID_          VARCHAR2(40),
  VAR_TYPE_         VARCHAR2(20),
  VAR_NAME_         VARCHAR2(60),
  VALUE_            VARCHAR2(3000),
  OBJ_              BLOB,
  CREATION_DATE_    TIMESTAMP(6)
)
;
comment on table FF_NODE_VAR_OP
  is '节点变量操作';
comment on column FF_NODE_VAR_OP.NODE_VAR_OP_ID_
  is '节点变量操作ID';
comment on column FF_NODE_VAR_OP.OPERATION_ID_
  is '操作ID';
comment on column FF_NODE_VAR_OP.OPERATION_TYPE_
  is '操作类型';
comment on column FF_NODE_VAR_OP.OPERATION_ORDER_
  is '操作顺序';
comment on column FF_NODE_VAR_OP.OPERATION_DATE_
  is '操作日期';
comment on column FF_NODE_VAR_OP.OPERATION_STATUS_
  is '操作状态';
comment on column FF_NODE_VAR_OP.NODE_VAR_ID_
  is '节点变量ID';
comment on column FF_NODE_VAR_OP.NODE_ID_
  is '节点ID';
comment on column FF_NODE_VAR_OP.VAR_TYPE_
  is '变量类型';
comment on column FF_NODE_VAR_OP.VAR_NAME_
  is '变量名称';
comment on column FF_NODE_VAR_OP.VALUE_
  is '值';
comment on column FF_NODE_VAR_OP.OBJ_
  is '对象';
comment on column FF_NODE_VAR_OP.CREATION_DATE_
  is '创建日期';
alter table FF_NODE_VAR_OP
  add constraint PK_FF_NODE_VAR_OP primary key (NODE_VAR_OP_ID_);
alter table FF_NODE_VAR_OP
  add constraint FK_FF_NODE_VAR_OP_OPERATION foreign key (OPERATION_ID_)
  references FF_OPERATION (OPERATION_ID_);

prompt
prompt Creating table FF_OPERATION_FOLLOW_UP
prompt =====================================
prompt
create table FF_OPERATION_FOLLOW_UP
(
  OPERATION_FOLLOW_UP_ID_ VARCHAR2(40) not null,
  OPERATION_ID_           VARCHAR2(40) not null,
  FOLLOW_UP_OPERATION_ID_ VARCHAR2(40) not null,
  OPERATION_DATE_         TIMESTAMP(6) not null
)
;
comment on table FF_OPERATION_FOLLOW_UP
  is '操作后续';
comment on column FF_OPERATION_FOLLOW_UP.OPERATION_FOLLOW_UP_ID_
  is '操作后续ID';
comment on column FF_OPERATION_FOLLOW_UP.OPERATION_ID_
  is '操作ID';
comment on column FF_OPERATION_FOLLOW_UP.FOLLOW_UP_OPERATION_ID_
  is '后续操作ID';
comment on column FF_OPERATION_FOLLOW_UP.OPERATION_DATE_
  is '操作日期';
alter table FF_OPERATION_FOLLOW_UP
  add constraint PK_FF_OPERATION_FOLOW_UP primary key (OPERATION_FOLLOW_UP_ID_);
alter table FF_OPERATION_FOLLOW_UP
  add constraint FK_FF_OPERATION_FOLOW_UP_O foreign key (OPERATION_ID_)
  references FF_OPERATION (OPERATION_ID_);
alter table FF_OPERATION_FOLLOW_UP
  add constraint FK_FF_OPERATION_FOLOW_UP_OFU foreign key (FOLLOW_UP_OPERATION_ID_)
  references FF_OPERATION (OPERATION_ID_);

prompt
prompt Creating table FF_PROC_OP
prompt =========================
prompt
create table FF_PROC_OP
(
  PROC_OP_ID_               VARCHAR2(40) not null,
  OPERATION_ID_             VARCHAR2(40) not null,
  OPERATION_TYPE_           VARCHAR2(20) not null,
  OPERATION_ORDER_          INTEGER,
  OPERATION_DATE_           TIMESTAMP(6),
  OPERATION_STATUS_         VARCHAR2(20),
  PROC_ID_                  VARCHAR2(40) not null,
  PROC_DEF_ID_              VARCHAR2(40),
  ADJUST_PROC_DEF_ID_       VARCHAR2(40),
  ISOLATE_SUB_PROC_NODE_ID_ VARCHAR2(40),
  BIZ_ID_                   VARCHAR2(40),
  BIZ_TYPE_                 VARCHAR2(60),
  BIZ_CODE_                 VARCHAR2(100),
  BIZ_NAME_                 VARCHAR2(100),
  BIZ_DESC_                 VARCHAR2(300),
  PROC_START_USER_          VARCHAR2(40),
  PROC_START_USER_NAME_     VARCHAR2(60),
  PROC_END_USER_            VARCHAR2(40),
  PROC_END_USER_NAME_       VARCHAR2(60),
  PROC_END_DATE_            TIMESTAMP(6),
  PROC_STATUS_              VARCHAR2(20),
  CREATION_DATE_            TIMESTAMP(6)
)
;
comment on table FF_PROC_OP
  is '流程操作';
comment on column FF_PROC_OP.PROC_OP_ID_
  is '流程操作ID';
comment on column FF_PROC_OP.OPERATION_ID_
  is '操作ID';
comment on column FF_PROC_OP.OPERATION_TYPE_
  is '操作类型';
comment on column FF_PROC_OP.OPERATION_ORDER_
  is '操作顺序';
comment on column FF_PROC_OP.OPERATION_DATE_
  is '操作日期';
comment on column FF_PROC_OP.OPERATION_STATUS_
  is '操作状态';
comment on column FF_PROC_OP.PROC_ID_
  is '流程ID';
comment on column FF_PROC_OP.PROC_DEF_ID_
  is '流程定义ID';
comment on column FF_PROC_OP.ADJUST_PROC_DEF_ID_
  is '调整流程定义ID';
comment on column FF_PROC_OP.ISOLATE_SUB_PROC_NODE_ID_
  is '独立子流程所属节点ID';
comment on column FF_PROC_OP.BIZ_ID_
  is '业务主键';
comment on column FF_PROC_OP.BIZ_TYPE_
  is '业务类型';
comment on column FF_PROC_OP.BIZ_CODE_
  is '业务编码';
comment on column FF_PROC_OP.BIZ_NAME_
  is '业务名称';
comment on column FF_PROC_OP.BIZ_DESC_
  is '业务备注';
comment on column FF_PROC_OP.PROC_START_USER_
  is '流程开始人员';
comment on column FF_PROC_OP.PROC_START_USER_NAME_
  is '流程开始人员名称';
comment on column FF_PROC_OP.PROC_END_USER_
  is '流程完成人员';
comment on column FF_PROC_OP.PROC_END_USER_NAME_
  is '流程完成人员名称';
comment on column FF_PROC_OP.PROC_END_DATE_
  is '流程完成日期';
comment on column FF_PROC_OP.PROC_STATUS_
  is '流程状态';
comment on column FF_PROC_OP.CREATION_DATE_
  is '创建日期';
alter table FF_PROC_OP
  add constraint PK_FF_PROC_OP primary key (PROC_OP_ID_);
alter table FF_PROC_OP
  add constraint FK_FF_PROC_OP_OPERATION foreign key (OPERATION_ID_)
  references FF_OPERATION (OPERATION_ID_);

prompt
prompt Creating table FF_TASK
prompt ======================
prompt
create table FF_TASK
(
  TASK_ID_            VARCHAR2(40) not null,
  NODE_ID_            VARCHAR2(40),
  PREVIOUS_TASK_ID_   VARCHAR2(40),
  TASK_TYPE_          VARCHAR2(20) not null,
  ASSIGNEE_           VARCHAR2(40),
  ASSIGNEE_NAME_      VARCHAR2(60),
  ACTION_             VARCHAR2(300),
  DUE_DATE_           TIMESTAMP(6),
  CLAIM_              VARCHAR2(20) not null,
  FORWARDABLE_        VARCHAR2(20) not null,
  PRIORITY_           INTEGER not null,
  FORWARD_STATUS_     VARCHAR2(20) not null,
  TASK_END_USER_      VARCHAR2(40),
  TASK_END_USER_NAME_ VARCHAR2(60),
  TASK_END_DATE_      TIMESTAMP(6),
  NEXT_CANDIDATE_     CLOB,
  TASK_STATUS_        VARCHAR2(20) not null,
  CREATION_DATE_      TIMESTAMP(6) not null
)
;
comment on table FF_TASK
  is '任务';
comment on column FF_TASK.TASK_ID_
  is '任务ID';
comment on column FF_TASK.NODE_ID_
  is '节点ID';
comment on column FF_TASK.PREVIOUS_TASK_ID_
  is '前一个任务ID';
comment on column FF_TASK.TASK_TYPE_
  is '任务类型';
comment on column FF_TASK.ASSIGNEE_
  is '办理人';
comment on column FF_TASK.ASSIGNEE_NAME_
  is '办理人名称';
comment on column FF_TASK.ACTION_
  is '业务行为';
comment on column FF_TASK.DUE_DATE_
  is '截止日期';
comment on column FF_TASK.CLAIM_
  is '认领';
comment on column FF_TASK.FORWARDABLE_
  is '可转发';
comment on column FF_TASK.PRIORITY_
  is '优先级';
comment on column FF_TASK.FORWARD_STATUS_
  is '转发状态';
comment on column FF_TASK.TASK_END_USER_
  is '任务完成人员';
comment on column FF_TASK.TASK_END_USER_NAME_
  is '任务完成人员名称';
comment on column FF_TASK.TASK_END_DATE_
  is '任务完成日期';
comment on column FF_TASK.NEXT_CANDIDATE_
  is '下个候选人';
comment on column FF_TASK.TASK_STATUS_
  is '任务状态';
comment on column FF_TASK.CREATION_DATE_
  is '创建日期';
alter table FF_TASK
  add constraint PK_FF_TASK primary key (TASK_ID_);
alter table FF_TASK
  add constraint FK_FF_TASK_NODE foreign key (NODE_ID_)
  references FF_NODE (NODE_ID_);
alter table FF_TASK
  add constraint FK_FF_TASK_PARENT foreign key (PREVIOUS_TASK_ID_)
  references FF_TASK (TASK_ID_);

prompt
prompt Creating table FF_TASK_OP
prompt =========================
prompt
create table FF_TASK_OP
(
  TASK_OP_ID_         VARCHAR2(40) not null,
  OPERATION_ID_       VARCHAR2(40) not null,
  OPERATION_TYPE_     VARCHAR2(20) not null,
  OPERATION_ORDER_    INTEGER,
  OPERATION_DATE_     TIMESTAMP(6),
  OPERATION_STATUS_   VARCHAR2(20),
  TASK_ID_            VARCHAR2(40) not null,
  NODE_ID_            VARCHAR2(40),
  PREVIOUS_TASK_ID_   VARCHAR2(40),
  TASK_TYPE_          VARCHAR2(20),
  ASSIGNEE_           VARCHAR2(40),
  ASSIGNEE_NAME_      VARCHAR2(60),
  ACTION_             VARCHAR2(300),
  DUE_DATE_           TIMESTAMP(6),
  CLAIM_              VARCHAR2(20),
  FORWARDABLE_        VARCHAR2(20),
  PRIORITY_           INTEGER,
  FORWARD_STATUS_     VARCHAR2(20),
  TASK_END_USER_      VARCHAR2(40),
  TASK_END_USER_NAME_ VARCHAR2(60),
  TASK_END_DATE_      TIMESTAMP(6),
  NEXT_CANDIDATE_     CLOB,
  TASK_STATUS_        VARCHAR2(20),
  CREATION_DATE_      TIMESTAMP(6)
)
;
comment on table FF_TASK_OP
  is '任务操作';
comment on column FF_TASK_OP.TASK_OP_ID_
  is '任务操作ID';
comment on column FF_TASK_OP.OPERATION_ID_
  is '操作ID';
comment on column FF_TASK_OP.OPERATION_TYPE_
  is '操作类型';
comment on column FF_TASK_OP.OPERATION_ORDER_
  is '操作顺序';
comment on column FF_TASK_OP.OPERATION_DATE_
  is '操作日期';
comment on column FF_TASK_OP.OPERATION_STATUS_
  is '操作状态';
comment on column FF_TASK_OP.TASK_ID_
  is '任务ID';
comment on column FF_TASK_OP.NODE_ID_
  is '节点ID';
comment on column FF_TASK_OP.PREVIOUS_TASK_ID_
  is '前一个任务ID';
comment on column FF_TASK_OP.TASK_TYPE_
  is '任务类型';
comment on column FF_TASK_OP.ASSIGNEE_
  is '办理人';
comment on column FF_TASK_OP.ASSIGNEE_NAME_
  is '办理人名称';
comment on column FF_TASK_OP.ACTION_
  is '业务行为';
comment on column FF_TASK_OP.DUE_DATE_
  is '截止日期';
comment on column FF_TASK_OP.CLAIM_
  is '认领';
comment on column FF_TASK_OP.FORWARDABLE_
  is '可转发';
comment on column FF_TASK_OP.PRIORITY_
  is '优先级';
comment on column FF_TASK_OP.FORWARD_STATUS_
  is '转发状态';
comment on column FF_TASK_OP.TASK_END_USER_
  is '任务完成人员';
comment on column FF_TASK_OP.TASK_END_USER_NAME_
  is '任务完成人员名称';
comment on column FF_TASK_OP.TASK_END_DATE_
  is '任务完成日期';
comment on column FF_TASK_OP.NEXT_CANDIDATE_
  is '下个候选人';
comment on column FF_TASK_OP.TASK_STATUS_
  is '任务状态';
comment on column FF_TASK_OP.CREATION_DATE_
  is '创建日期';
alter table FF_TASK_OP
  add constraint PK_FF_TASK_OP primary key (TASK_OP_ID_);
alter table FF_TASK_OP
  add constraint FK_FF_TASK_OP_OPERATION foreign key (OPERATION_ID_)
  references FF_OPERATION (OPERATION_ID_);

prompt
prompt Creating view FFV_ADJUST_PROC_DEF
prompt =================================
prompt
create or replace view ffv_adjust_proc_def as
select APD.ADJUST_PROC_DEF_ID_, APD.PROC_DEF_ID_, APD.PROC_DEF_MODEL_, APD.PROC_DEF_DIAGRAM_FILE_, APD.PROC_DEF_DIAGRAM_FILE_NAME_, APD.PROC_DEF_DIAGRAM_FILE_LENGTH_, APD.PROC_DEF_DIAGRAM_WIDTH_, APD.PROC_DEF_DIAGRAM_HEIGHT_, APD.CREATION_DATE_, APD.UPDATE_DATE_, APD.OPERATOR_ID_, APD.OPERATOR_NAME_ from FF_ADJUST_PROC_DEF APD;

prompt
prompt Creating view FFV_DELEGATE
prompt ==========================
prompt
create or replace view ffv_delegate as
select D.DELEGATE_ID_, D.ASSIGNEE_, D.ASSIGNEE_NAME_, D.DELEGATOR_, D.DELEGATOR_NAME_, D.START_DATE_, D.END_DATE_ from FF_DELEGATE D;

prompt
prompt Creating view FFV_NODE
prompt ======================
prompt
create or replace view ffv_node as
select N.NODE_ID_, N.PARENT_NODE_ID_, N.PROC_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.ASSIGNEE_, N.ACTION_, N.DUE_DATE_, N.CLAIM_, N.FORWARDABLE_, N.PRIORITY_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.NEXT_CANDIDATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ from FF_NODE N;

prompt
prompt Creating view FFV_NODE_P
prompt ========================
prompt
create or replace view ffv_node_p as
select N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.ASSIGNEE_, N.ACTION_, N.DUE_DATE_, N.CLAIM_, N.FORWARDABLE_, N.PRIORITY_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.NEXT_CANDIDATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_, P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_
  from FF_NODE N
 inner join FF_PROC P
    on P.PROC_ID_ = N.PROC_ID_;

prompt
prompt Creating view FFV_NODE_PD
prompt =========================
prompt
create or replace view ffv_node_pd as
select N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.ASSIGNEE_, N.ACTION_, N.DUE_DATE_, N.CLAIM_, N.FORWARDABLE_, N.PRIORITY_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.NEXT_CANDIDATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_, P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_, SPD.PROC_DEF_CODE_ as SUB_PROC_DEF_CODE_
  from FF_NODE N
 inner join FF_PROC P on P.PROC_ID_ = N.PROC_ID_
 inner join FF_PROC_DEF PD on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_
 inner join FF_PROC_DEF SPD on SPD.PROC_DEF_ID_ = N.SUB_PROC_DEF_ID_;

prompt
prompt Creating view FFV_NODE_VAR
prompt ==========================
prompt
create or replace view ffv_node_var as
select PV.NODE_VAR_ID_, PV.NODE_ID_, PV.VAR_TYPE_, PV.VAR_NAME_, PV.VALUE_, PV.OBJ_, PV.CREATION_DATE_, N.PARENT_NODE_ID_, N.PROC_ID_ from FF_NODE_VAR PV inner join FF_NODE N on N.NODE_ID_ = PV.NODE_ID_;

prompt
prompt Creating view FFV_OPERATION
prompt ===========================
prompt
create or replace view ffv_operation as
select O.OPERATION_ID_, O.OPERATION_, O.PROC_ID_, O.NODE_ID_, O.TASK_ID_, O.MEMO_, O.OPERATOR_, O.OPERATOR_NAME_, O.OPERATION_DATE_, O.OPERATION_STATUS_ from FF_OPERATION O;

prompt
prompt Creating view FFV_PROC
prompt ======================
prompt
create or replace view ffv_proc as
select P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ from FF_PROC P;

prompt
prompt Creating view FFV_OPERATION_P
prompt =============================
prompt
create or replace view ffv_operation_p as
select O.OPERATION_ID_, O.OPERATION_, O.NODE_ID_, O.TASK_ID_, O.MEMO_, O.OPERATOR_, O.OPERATOR_NAME_, O.OPERATION_DATE_, O.OPERATION_STATUS_, P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ from FF_OPERATION O left outer join FF_PROC P on P.PROC_ID_ = O.PROC_ID_;

prompt
prompt Creating view FFV_OPERATION_PD
prompt ==============================
prompt
create or replace view ffv_operation_pd as
select O.OPERATION_ID_, O.OPERATION_, O.NODE_ID_, O.TASK_ID_, O.MEMO_, O.OPERATOR_, O.OPERATOR_NAME_, O.OPERATION_DATE_, O.OPERATION_STATUS_, P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_ from FF_OPERATION O left outer join FF_PROC P on P.PROC_ID_ = O.PROC_ID_ inner join FF_PROC_DEF PD on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_;

prompt
prompt Creating view FFV_PROC_DEF
prompt ==========================
prompt
create or replace view ffv_proc_def as
select PROC_DEF_ID_, PROC_DEF_CODE_, PROC_DEF_NAME_, PROC_DEF_CAT_, PROC_DEF_MODEL_, PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_, PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_, MEMO_, EXT_ATTR_1_, EXT_ATTR_2_, EXT_ATTR_3_, EXT_ATTR_4_, EXT_ATTR_5_, EXT_ATTR_6_, EXT_ATTR_7_, EXT_ATTR_8_, VERSION_, PROC_DEF_STATUS_, CREATION_DATE_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_ from FF_PROC_DEF;

prompt
prompt Creating view FFV_PROC_PD
prompt =========================
prompt
create or replace view ffv_proc_pd as
select P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_ from FF_PROC P inner join FF_PROC_DEF PD on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_;

prompt
prompt Creating view FFV_TASK
prompt ======================
prompt
create or replace view ffv_task as
select T.TASK_ID_, T.NODE_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_ from FF_TASK T;

prompt
prompt Creating view FFV_TASK_N
prompt ========================
prompt
create or replace view ffv_task_n as
select T.TASK_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_, N.NODE_ID_, N.PARENT_NODE_ID_, N.PROC_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ as NODE_CREATION_DATE_
  from FF_TASK T
 inner join FF_NODE N
    on N.NODE_ID_ = T.NODE_ID_;

prompt
prompt Creating view FFV_TASK_P
prompt ========================
prompt
create or replace view ffv_task_p as
select T.TASK_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_, N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ as NODE_CREATION_DATE_, P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_
  from FF_TASK T
 inner join FF_NODE N
    on N.NODE_ID_ = T.NODE_ID_
 inner join FF_PROC P
    on P.PROC_ID_ = N.PROC_ID_;

prompt
prompt Creating view FFV_TASK_PD
prompt =========================
prompt
create or replace view ffv_task_pd as
select T.TASK_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_, N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ as NODE_CREATION_DATE_, P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_
  from FF_TASK T
 inner join FF_NODE N
    on N.NODE_ID_ = T.NODE_ID_
 inner join FF_PROC P
    on P.PROC_ID_ = N.PROC_ID_
 inner join FF_PROC_DEF PD
    on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_;
