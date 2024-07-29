/*==============================================================*/
/* Table: FF_ADJUST_PROC_DEF                                    */
/*==============================================================*/
create table FF_ADJUST_PROC_DEF
(
   ADJUST_PROC_DEF_ID_  varchar(40) not null comment '调整流程定义ID',
   PROC_DEF_ID_         varchar(40) not null comment '流程定义ID',
   PROC_DEF_MODEL_      text not null comment '流程定义模型',
   PROC_DEF_DIAGRAM_FILE_ longblob comment '流程定义图文件',
   PROC_DEF_DIAGRAM_FILE_NAME_ varchar(300) comment '流程定义图文件名称',
   PROC_DEF_DIAGRAM_FILE_LENGTH_ numeric(8,0) comment '流程定义图文件长度',
   PROC_DEF_DIAGRAM_WIDTH_ numeric(8,0) comment '流程定义图宽度',
   PROC_DEF_DIAGRAM_HEIGHT_ numeric(8,0) comment '流程定义图高度',
   CREATION_DATE_       datetime comment '创建日期',
   UPDATE_DATE_         datetime comment '更新日期',
   OPERATOR_ID_         varchar(40) comment '操作人员ID',
   OPERATOR_NAME_       varchar(60) comment '操作人员名称',
   primary key (ADJUST_PROC_DEF_ID_)
);

alter table FF_ADJUST_PROC_DEF comment '调整流程定义';

/*==============================================================*/
/* Table: FF_DELEGATE                                           */
/*==============================================================*/
create table FF_DELEGATE
(
   DELEGATE_ID_         varchar(40) not null comment '代理ID',
   ASSIGNEE_            varchar(60) comment '办理人',
   ASSIGNEE_NAME_       varchar(60) comment '办理人名称',
   DELEGATOR_           varchar(60) not null comment '代理人',
   DELEGATOR_NAME_      varchar(60) comment '代理人名称',
   START_DATE_          datetime comment '开始日期',
   END_DATE_            datetime comment '结束日期',
   primary key (DELEGATE_ID_)
);

alter table FF_DELEGATE comment '代理';

/*==============================================================*/
/* Table: FF_NODE                                               */
/*==============================================================*/
create table FF_NODE
(
   NODE_ID_             varchar(40) not null comment '节点ID',
   PARENT_NODE_ID_      varchar(40) comment '上级节点ID',
   PROC_ID_             varchar(40) not null comment '流程ID',
   PREVIOUS_NODE_IDS_   varchar(280) comment '前节点IDs',
   LAST_COMPLETE_NODE_IDS_ varchar(280) comment '最后完成节点IDs',
   SUB_PROC_DEF_ID_     varchar(40) comment '子流程定义ID',
   ADJUST_SUB_PROC_DEF_ID_ varchar(40) comment '调整子流程定义ID',
   NODE_TYPE_           varchar(20) not null comment '节点类型',
   NODE_CODE_           varchar(60) comment '节点编码',
   NODE_NAME_           varchar(60) comment '节点名称',
   PARENT_NODE_CODE_    varchar(100) comment '上级节点编码',
   CANDIDATE_ASSIGNEE_  varchar(200) comment '候选人',
   COMPLETE_EXPRESSION_ varchar(200) comment '完成表达式',
   COMPLETE_RETURN_     varchar(200) comment '完成后返回前一个节点',
   EXCLUSIVE_           varchar(200) comment '排他',
   WAITING_FOR_COMPLETE_NODE_ varchar(200) comment '等待完成节点',
   AUTO_COMPLETE_SAME_ASSIGNEE_ varchar(200) comment '自动完成相同办理人任务',
   AUTO_COMPLETE_EMPTY_ASSIGNEE_ varchar(200) comment '自动完成没有办理人节点',
   INFORM_              varchar(200) comment '通知',
   ASSIGNEE_            varchar(200) comment '办理人',
   ACTION_              varchar(300) comment '业务行为',
   DUE_DATE_            varchar(200) comment '截止日期',
   CLAIM_               varchar(200) comment '认领',
   FORWARDABLE_         varchar(200) comment '可转发',
   PRIORITY_            varchar(200) comment '优先级',
   NODE_END_USER_       varchar(40) comment '节点完成人员',
   NODE_END_USER_NAME_  varchar(60) comment '节点完成人员名称',
   NODE_END_DATE_       datetime comment '节点完成日期',
   NEXT_CANDIDATE_      text comment '下一个候选',
   ISOLATE_SUB_PROC_DEF_CODE_ varchar(60) comment '独立子流程定义编码',
   ISOLATE_SUB_PROC_CANDIDATE_ varchar(500) comment '独立子流程候选',
   ISOLATE_SUB_PROC_STATUS_ varchar(60) comment '独立子流程状态',
   NODE_STATUS_         varchar(20) not null comment '节点状态',
   CREATION_DATE_       datetime not null comment '创建日期',
   primary key (NODE_ID_)
);

alter table FF_NODE comment '节点';

/*==============================================================*/
/* Table: FF_NODE_OP                                            */
/*==============================================================*/
create table FF_NODE_OP
(
   NODE_OP_ID_          varchar(40) not null comment '节点操作ID',
   OPERATION_ID_        varchar(40) not null comment '操作ID',
   OPERATION_TYPE_      varchar(20) not null comment '操作类型',
   OPERATION_ORDER_     numeric(8,0) comment '操作顺序',
   OPERATION_DATE_      datetime comment '操作日期',
   OPERATION_STATUS_    varchar(20) comment '操作状态',
   NODE_ID_             varchar(40) not null comment '节点ID',
   PARENT_NODE_ID_      varchar(40) comment '上级节点ID',
   PROC_ID_             varchar(40) comment '流程ID',
   PREVIOUS_NODE_IDS_   varchar(280) comment '前节点IDs',
   LAST_COMPLETE_NODE_IDS_ varchar(280) comment '最后完成节点IDs',
   SUB_PROC_DEF_ID_     varchar(40) comment '子流程定义ID',
   ADJUST_SUB_PROC_DEF_ID_ varchar(40) comment '调整子流程定义ID',
   NODE_TYPE_           varchar(20) comment '节点类型',
   NODE_CODE_           varchar(60) comment '节点编码',
   NODE_NAME_           varchar(60) comment '节点名称',
   PARENT_NODE_CODE_    varchar(60) comment '上级节点编码',
   CANDIDATE_ASSIGNEE_  varchar(200) comment '候选人',
   COMPLETE_EXPRESSION_ varchar(200) comment '完成表达式',
   COMPLETE_RETURN_     varchar(200) comment '完成后返回前一个节点',
   EXCLUSIVE_           varchar(200) comment '排他',
   WAITING_FOR_COMPLETE_NODE_ varchar(200) comment '等待完成节点',
   AUTO_COMPLETE_SAME_ASSIGNEE_ varchar(200) comment '自动完成相同办理人任务',
   AUTO_COMPLETE_EMPTY_ASSIGNEE_ varchar(200) comment '自动完成没有办理人节点',
   INFORM_              varchar(200) comment '通知',
   ASSIGNEE_            varchar(200) comment '办理人',
   ACTION_              varchar(200) comment '业务行为',
   DUE_DATE_            varchar(200) comment '截止日期',
   CLAIM_               varchar(200) comment '认领',
   FORWARDABLE_         varchar(200) comment '可转发',
   PRIORITY_            varchar(200) comment '优先级',
   NODE_END_USER_       varchar(40) comment '节点完成人员',
   NODE_END_USER_NAME_  varchar(60) comment '节点完成人员名称',
   NODE_END_DATE_       datetime comment '节点完成日期',
   NEXT_CANDIDATE_      text comment '下一个候选',
   ISOLATE_SUB_PROC_DEF_CODE_ varchar(60) comment '独立子流程定义编码',
   ISOLATE_SUB_PROC_CANDIDATE_ varchar(500) comment '独立子流程候选',
   ISOLATE_SUB_PROC_STATUS_ varchar(60) comment '独立子流程状态',
   NODE_STATUS_         varchar(20) comment '节点状态',
   CREATION_DATE_       datetime comment '创建日期',
   primary key (NODE_OP_ID_)
);

alter table FF_NODE_OP comment '节点操作';

/*==============================================================*/
/* Table: FF_NODE_VAR                                           */
/*==============================================================*/
create table FF_NODE_VAR
(
   NODE_VAR_ID_         varchar(40) not null comment '节点变量ID',
   NODE_ID_             varchar(40) not null comment '节点ID',
   VAR_TYPE_            varchar(20) not null comment '变量类型',
   VAR_NAME_            varchar(60) not null comment '变量名称',
   VALUE_               varchar(3000) comment '值',
   OBJ_                 longblob comment '对象',
   CREATION_DATE_       datetime not null comment '创建日期',
   primary key (NODE_VAR_ID_)
);

alter table FF_NODE_VAR comment '节点变量';

/*==============================================================*/
/* Index: IX_SUB_PROC_VAR_NAME                                  */
/*==============================================================*/
create index IX_SUB_PROC_VAR_NAME on FF_NODE_VAR
(
   VAR_NAME_
);

/*==============================================================*/
/* Table: FF_NODE_VAR_OP                                        */
/*==============================================================*/
create table FF_NODE_VAR_OP
(
   NODE_VAR_OP_ID_      varchar(40) not null comment '节点变量操作ID',
   OPERATION_ID_        varchar(40) not null comment '操作ID',
   OPERATION_TYPE_      varchar(20) not null comment '操作类型',
   OPERATION_ORDER_     numeric(8,0) comment '操作顺序',
   OPERATION_DATE_      datetime comment '操作日期',
   OPERATION_STATUS_    varchar(20) comment '操作状态',
   NODE_VAR_ID_         varchar(40) not null comment '节点变量ID',
   NODE_ID_             varchar(40) comment '节点ID',
   VAR_TYPE_            varchar(20) comment '变量类型',
   VAR_NAME_            varchar(60) comment '变量名称',
   VALUE_               varchar(3000) comment '值',
   OBJ_                 longblob comment '对象',
   CREATION_DATE_       datetime comment '创建日期',
   primary key (NODE_VAR_OP_ID_)
);

alter table FF_NODE_VAR_OP comment '节点变量操作';

/*==============================================================*/
/* Table: FF_OPERATION                                          */
/*==============================================================*/
create table FF_OPERATION
(
   OPERATION_ID_        varchar(40) not null comment '操作ID',
   OPERATION_           varchar(40) not null comment '操作',
   PROC_ID_             varchar(40) comment '流程ID',
   NODE_ID_             varchar(40) comment '节点ID',
   TASK_ID_             varchar(40) comment '任务ID',
   MEMO_                varchar(1000) comment '备注',
   OPERATOR_            varchar(40) comment '操作人',
   OPERATOR_NAME_       varchar(60) comment '操作人名称',
   OPERATION_DATE_      datetime not null comment '操作日期',
   OPERATION_STATUS_    varchar(20) not null comment '操作状态',
   primary key (OPERATION_ID_)
);

alter table FF_OPERATION comment '操作';

/*==============================================================*/
/* Index: IX_FF_OPERATION_OPERATOR                              */
/*==============================================================*/
create index IX_FF_OPERATION_OPERATOR on FF_OPERATION
(
   OPERATOR_
);

/*==============================================================*/
/* Index: IX_FF_OPERATION_PROC                                  */
/*==============================================================*/
create index IX_FF_OPERATION_PROC on FF_OPERATION
(
   PROC_ID_
);

/*==============================================================*/
/* Table: FF_OPERATION_FOLLOW_UP                                */
/*==============================================================*/
create table FF_OPERATION_FOLLOW_UP
(
   OPERATION_FOLLOW_UP_ID_ varchar(40) not null comment '操作后续ID',
   OPERATION_ID_        varchar(40) not null comment '操作ID',
   FOLLOW_UP_OPERATION_ID_ varchar(40) not null comment '后续操作ID',
   OPERATION_DATE_      datetime not null comment '操作日期',
   primary key (OPERATION_FOLLOW_UP_ID_)
);

alter table FF_OPERATION_FOLLOW_UP comment '操作后续';

/*==============================================================*/
/* Table: FF_PROC                                               */
/*==============================================================*/
create table FF_PROC
(
   PROC_ID_             varchar(40) not null comment '流程ID',
   PROC_DEF_ID_         varchar(40) not null comment '流程定义ID',
   ADJUST_PROC_DEF_ID_  varchar(40) comment '调整流程定义ID',
   ISOLATE_SUB_PROC_NODE_ID_ varchar(40) comment '独立子流程所属节点ID',
   BIZ_ID_              varchar(40) comment '业务主键',
   BIZ_TYPE_            varchar(60) comment '业务类型',
   BIZ_CODE_            varchar(100) comment '业务编码',
   BIZ_NAME_            varchar(100) comment '业务名称',
   BIZ_DESC_            varchar(300) comment '业务备注',
   PROC_START_USER_     varchar(40) comment '流程开始人员',
   PROC_START_USER_NAME_ varchar(60) comment '流程开始人员名称',
   PROC_END_USER_       varchar(40) comment '流程完成人员',
   PROC_END_USER_NAME_  varchar(60) comment '流程完成人员名称',
   PROC_END_DATE_       datetime comment '流程完成日期',
   PROC_STATUS_         varchar(20) not null comment '流程状态',
   CREATION_DATE_       datetime not null comment '创建日期',
   primary key (PROC_ID_)
);

alter table FF_PROC comment '流程';

/*==============================================================*/
/* Table: FF_PROC_DEF                                           */
/*==============================================================*/
create table FF_PROC_DEF
(
   PROC_DEF_ID_         varchar(40) not null comment '流程定义ID',
   PROC_DEF_CODE_       varchar(60) not null comment '流程定义编码',
   PROC_DEF_NAME_       varchar(60) not null comment '流程定义名称',
   PROC_DEF_CAT_        varchar(100) comment '流程定义分类',
   PROC_DEF_MODEL_      text not null comment '流程定义模型',
   PROC_DEF_DIAGRAM_FILE_ longblob comment '流程定义图文件',
   PROC_DEF_DIAGRAM_FILE_NAME_ varchar(300) comment '流程定义图文件名称',
   PROC_DEF_DIAGRAM_FILE_LENGTH_ numeric(8,0) comment '流程定义图文件长度',
   PROC_DEF_DIAGRAM_WIDTH_ numeric(8,0) comment '流程定义图宽度',
   PROC_DEF_DIAGRAM_HEIGHT_ numeric(8,0) comment '流程定义图高度',
   MEMO_                varchar(300) comment '备注',
   VERSION_             numeric(8,0) not null comment '版本',
   EXT_ATTR_1_          varchar(120) comment '扩展属性1',
   EXT_ATTR_2_          varchar(120) comment '扩展属性2',
   EXT_ATTR_3_          varchar(120) comment '扩展属性3',
   EXT_ATTR_4_          varchar(120) comment '扩展属性4',
   EXT_ATTR_5_          varchar(120) comment '扩展属性5',
   EXT_ATTR_6_          varchar(120) comment '扩展属性6',
   EXT_ATTR_7_          varchar(120) comment '扩展属性7',
   EXT_ATTR_8_          varchar(120) comment '扩展属性8',
   PROC_DEF_STATUS_     varchar(20) not null comment '流程定义状态',
   CREATION_DATE_       datetime comment '创建日期',
   UPDATE_DATE_         datetime comment '更新日期',
   OPERATOR_ID_         varchar(40) comment '操作人员ID',
   OPERATOR_NAME_       varchar(60) comment '操作人员名称',
   primary key (PROC_DEF_ID_),
   key UQ_FF_PROC_DEF (PROC_DEF_CODE_, VERSION_)
);

alter table FF_PROC_DEF comment '流程定义';

/*==============================================================*/
/* Table: FF_PROC_OP                                            */
/*==============================================================*/
create table FF_PROC_OP
(
   PROC_OP_ID_          varchar(40) not null comment '流程操作ID',
   OPERATION_ID_        varchar(40) not null comment '操作ID',
   OPERATION_TYPE_      varchar(20) not null comment '操作类型',
   OPERATION_ORDER_     numeric(8,0) comment '操作顺序',
   OPERATION_DATE_      datetime comment '操作日期',
   OPERATION_STATUS_    varchar(20) comment '操作状态',
   PROC_ID_             varchar(40) not null comment '流程ID',
   PROC_DEF_ID_         varchar(40) comment '流程定义ID',
   ADJUST_PROC_DEF_ID_  varchar(40) comment '调整流程定义ID',
   ISOLATE_SUB_PROC_NODE_ID_ varchar(40) comment '独立子流程所属节点ID',
   BIZ_ID_              varchar(40) comment '业务主键',
   BIZ_TYPE_            varchar(60) comment '业务类型',
   BIZ_CODE_            varchar(100) comment '业务编码',
   BIZ_NAME_            varchar(100) comment '业务名称',
   BIZ_DESC_            varchar(300) comment '业务备注',
   PROC_START_USER_     varchar(40) comment '流程开始人员',
   PROC_START_USER_NAME_ varchar(60) comment '流程开始人员名称',
   PROC_END_USER_       varchar(40) comment '流程完成人员',
   PROC_END_USER_NAME_  varchar(60) comment '流程完成人员名称',
   PROC_END_DATE_       datetime comment '流程完成日期',
   PROC_STATUS_         varchar(20) comment '流程状态',
   CREATION_DATE_       datetime comment '创建日期',
   primary key (PROC_OP_ID_)
);

alter table FF_PROC_OP comment '流程操作';

/*==============================================================*/
/* Table: FF_TASK                                               */
/*==============================================================*/
create table FF_TASK
(
   TASK_ID_             varchar(40) not null comment '任务ID',
   NODE_ID_             varchar(40) comment '节点ID',
   PREVIOUS_TASK_ID_    varchar(40) comment '前一个任务ID',
   TASK_TYPE_           varchar(20) not null comment '任务类型',
   ASSIGNEE_            varchar(40) comment '办理人',
   ASSIGNEE_NAME_       varchar(60) comment '办理人名称',
   ACTION_              varchar(300) comment '业务行为',
   DUE_DATE_            datetime comment '截止日期',
   CLAIM_               varchar(20) not null comment '认领',
   FORWARDABLE_         varchar(20) not null comment '可转发',
   PRIORITY_            numeric(8,0) not null comment '优先级',
   FORWARD_STATUS_      varchar(20) not null comment '转发状态',
   TASK_END_USER_       varchar(40) comment '任务完成人员',
   TASK_END_USER_NAME_  varchar(60) comment '任务完成人员名称',
   TASK_END_DATE_       datetime comment '任务完成日期',
   NEXT_CANDIDATE_      text comment '下一个候选',
   TASK_STATUS_         varchar(20) not null comment '任务状态',
   CREATION_DATE_       datetime not null comment '创建日期',
   primary key (TASK_ID_)
);

alter table FF_TASK comment '任务';

/*==============================================================*/
/* Table: FF_TASK_OP                                            */
/*==============================================================*/
create table FF_TASK_OP
(
   TASK_OP_ID_          varchar(40) not null comment '任务操作ID',
   OPERATION_ID_        varchar(40) not null comment '操作ID',
   OPERATION_TYPE_      varchar(20) not null comment '操作类型',
   OPERATION_ORDER_     numeric(8,0) comment '操作顺序',
   OPERATION_DATE_      datetime comment '操作日期',
   OPERATION_STATUS_    varchar(20) comment '操作状态',
   TASK_ID_             varchar(40) not null comment '任务ID',
   NODE_ID_             varchar(40) comment '节点ID',
   PREVIOUS_TASK_ID_    varchar(40) comment '前一个任务ID',
   TASK_TYPE_           varchar(20) comment '任务类型',
   ASSIGNEE_            varchar(40) comment '办理人',
   ASSIGNEE_NAME_       varchar(60) comment '办理人名称',
   ACTION_              varchar(300) comment '业务行为',
   DUE_DATE_            datetime comment '截止日期',
   CLAIM_               varchar(20) comment '认领',
   FORWARDABLE_         varchar(20) comment '可转发',
   PRIORITY_            numeric(8,0) comment '优先级',
   FORWARD_STATUS_      varchar(20) comment '转发状态',
   TASK_END_USER_       varchar(40) comment '任务完成人员',
   TASK_END_USER_NAME_  varchar(60) comment '任务完成人员名称',
   TASK_END_DATE_       datetime comment '任务完成日期',
   NEXT_CANDIDATE_      text comment '下一个候选',
   TASK_STATUS_         varchar(20) comment '任务状态',
   CREATION_DATE_       datetime comment '创建日期',
   primary key (TASK_OP_ID_)
);

alter table FF_TASK_OP comment '任务操作';

alter table FF_ADJUST_PROC_DEF add constraint FK_FF_ADJUST_PROC_DEF_PROC_DEF foreign key (PROC_DEF_ID_)
      references FF_PROC_DEF (PROC_DEF_ID_) on update restrict;

alter table FF_NODE add constraint FK_FF_NODE_ADJUST_PROC_DEF foreign key (ADJUST_SUB_PROC_DEF_ID_)
      references FF_ADJUST_PROC_DEF (ADJUST_PROC_DEF_ID_) on update restrict;

alter table FF_NODE add constraint FK_FF_NODE_PARENT foreign key (PARENT_NODE_ID_)
      references FF_NODE (NODE_ID_) on update restrict;

alter table FF_NODE add constraint FK_FF_NODE_PROC foreign key (PROC_ID_)
      references FF_PROC (PROC_ID_) on update restrict;

alter table FF_NODE add constraint FK_FF_NODE_PROC_DEF foreign key (SUB_PROC_DEF_ID_)
      references FF_PROC_DEF (PROC_DEF_ID_) on update restrict;

alter table FF_NODE_OP add constraint FK_FF_NODE_OP_OPERATION foreign key (OPERATION_ID_)
      references FF_OPERATION (OPERATION_ID_) on update restrict;

alter table FF_NODE_VAR_OP add constraint FK_FF_NODE_VAR_OP_OPERATION foreign key (OPERATION_ID_)
      references FF_OPERATION (OPERATION_ID_) on update restrict;

alter table FF_OPERATION_FOLLOW_UP add constraint FK_FF_OPERATION_FOLOW_UP_O foreign key (OPERATION_ID_)
      references FF_OPERATION (OPERATION_ID_) on update restrict;

alter table FF_OPERATION_FOLLOW_UP add constraint FK_FF_OPERATION_FOLOW_UP_OFU foreign key (FOLLOW_UP_OPERATION_ID_)
      references FF_OPERATION (OPERATION_ID_) on update restrict;

alter table FF_PROC add constraint FK_FF_PROC_PROC_DEF foreign key (PROC_DEF_ID_)
      references FF_PROC_DEF (PROC_DEF_ID_) on update restrict;

alter table FF_PROC_OP add constraint FK_FF_PROC_OP_OPERATION foreign key (OPERATION_ID_)
      references FF_OPERATION (OPERATION_ID_) on update restrict;

alter table FF_TASK add constraint FK_FF_TASK_NODE foreign key (NODE_ID_)
      references FF_NODE (NODE_ID_) on update restrict;

alter table FF_TASK add constraint FK_FF_TASK_PARENT foreign key (PREVIOUS_TASK_ID_)
      references FF_TASK (TASK_ID_) on update restrict;

alter table FF_TASK_OP add constraint FK_FF_TASK_OP_OPERATION foreign key (OPERATION_ID_)
      references FF_OPERATION (OPERATION_ID_) on update restrict;

create or replace view FFV_ADJUST_PROC_DEF as
select APD.ADJUST_PROC_DEF_ID_, APD.PROC_DEF_ID_, APD.PROC_DEF_MODEL_, APD.PROC_DEF_DIAGRAM_FILE_, APD.PROC_DEF_DIAGRAM_FILE_NAME_, APD.PROC_DEF_DIAGRAM_FILE_LENGTH_, APD.PROC_DEF_DIAGRAM_WIDTH_, APD.PROC_DEF_DIAGRAM_HEIGHT_, APD.CREATION_DATE_, APD.UPDATE_DATE_, APD.OPERATOR_ID_, APD.OPERATOR_NAME_ from FF_ADJUST_PROC_DEF APD;

create or replace view FFV_DELEGATE as
select D.DELEGATE_ID_, D.ASSIGNEE_, D.ASSIGNEE_NAME_, D.DELEGATOR_, D.DELEGATOR_NAME_, D.START_DATE_, D.END_DATE_ from FF_DELEGATE D;

create or replace view FFV_NODE as
select N.NODE_ID_, N.PARENT_NODE_ID_, N.PROC_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.ASSIGNEE_, N.ACTION_, N.DUE_DATE_, N.CLAIM_, N.FORWARDABLE_, N.PRIORITY_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.NEXT_CANDIDATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ from FF_NODE N;

create or replace view FFV_NODE_P as
select N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.ASSIGNEE_, N.ACTION_, N.DUE_DATE_, N.CLAIM_, N.FORWARDABLE_, N.PRIORITY_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.NEXT_CANDIDATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_, P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_
  from FF_NODE N
 inner join FF_PROC P
    on P.PROC_ID_ = N.PROC_ID_;

create or replace view FFV_NODE_PD as
select N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.ASSIGNEE_, N.ACTION_, N.DUE_DATE_, N.CLAIM_, N.FORWARDABLE_, N.PRIORITY_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.NEXT_CANDIDATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_, P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_, SPD.PROC_DEF_CODE_ as SUB_PROC_DEF_CODE_
  from FF_NODE N
 inner join FF_PROC P on P.PROC_ID_ = N.PROC_ID_
 inner join FF_PROC_DEF PD on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_
 inner join FF_PROC_DEF SPD on SPD.PROC_DEF_ID_ = N.SUB_PROC_DEF_ID_;

create or replace view FFV_NODE_VAR as
select PV.NODE_VAR_ID_, PV.NODE_ID_, PV.VAR_TYPE_, PV.VAR_NAME_, PV.VALUE_, PV.OBJ_, PV.CREATION_DATE_, N.PARENT_NODE_ID_, N.PROC_ID_ from FF_NODE_VAR PV inner join FF_NODE N on N.NODE_ID_ = PV.NODE_ID_;

create or replace view FFV_OPERATION as
select O.OPERATION_ID_, O.OPERATION_, O.PROC_ID_, O.NODE_ID_, O.TASK_ID_, O.MEMO_, O.OPERATOR_, O.OPERATOR_NAME_, O.OPERATION_DATE_, O.OPERATION_STATUS_ from FF_OPERATION O;

create or replace view FFV_PROC as
select P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ from FF_PROC P;

create or replace view FFV_OPERATION_P as
select O.OPERATION_ID_, O.OPERATION_, O.NODE_ID_, O.TASK_ID_, O.MEMO_, O.OPERATOR_, O.OPERATOR_NAME_, O.OPERATION_DATE_, O.OPERATION_STATUS_, P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ from FF_OPERATION O left outer join FF_PROC P on P.PROC_ID_ = O.PROC_ID_;

create or replace view FFV_OPERATION_PD as
select O.OPERATION_ID_, O.OPERATION_, O.NODE_ID_, O.TASK_ID_, O.MEMO_, O.OPERATOR_, O.OPERATOR_NAME_, O.OPERATION_DATE_, O.OPERATION_STATUS_, P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_ from FF_OPERATION O left outer join FF_PROC P on P.PROC_ID_ = O.PROC_ID_ inner join FF_PROC_DEF PD on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_;

create or replace view FFV_PROC_DEF as
select PROC_DEF_ID_, PROC_DEF_CODE_, PROC_DEF_NAME_, PROC_DEF_CAT_, PROC_DEF_MODEL_, PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_, PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_, MEMO_, EXT_ATTR_1_, EXT_ATTR_2_, EXT_ATTR_3_, EXT_ATTR_4_, EXT_ATTR_5_, EXT_ATTR_6_, EXT_ATTR_7_, EXT_ATTR_8_, VERSION_, PROC_DEF_STATUS_, CREATION_DATE_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_ from FF_PROC_DEF;

create or replace view FFV_PROC_PD as
select P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_ from FF_PROC P inner join FF_PROC_DEF PD on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_;

create or replace view FFV_TASK as
select T.TASK_ID_, T.NODE_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_ from FF_TASK T;

create or replace view FFV_TASK_N as
select T.TASK_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_, N.NODE_ID_, N.PARENT_NODE_ID_, N.PROC_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ as NODE_CREATION_DATE_
  from FF_TASK T
 inner join FF_NODE N
    on N.NODE_ID_ = T.NODE_ID_;

create or replace view FFV_TASK_P as
select T.TASK_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_, N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ as NODE_CREATION_DATE_, P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_
  from FF_TASK T
 inner join FF_NODE N
    on N.NODE_ID_ = T.NODE_ID_
 inner join FF_PROC P
    on P.PROC_ID_ = N.PROC_ID_;

create or replace view FFV_TASK_PD as
select T.TASK_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.ACTION_, T.DUE_DATE_, T.CLAIM_, T.FORWARDABLE_, T.PRIORITY_, T.FORWARD_STATUS_, T.TASK_END_USER_, T.TASK_END_USER_NAME_, T.TASK_END_DATE_, T.NEXT_CANDIDATE_, T.TASK_STATUS_, T.CREATION_DATE_, N.NODE_ID_, N.PARENT_NODE_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.CANDIDATE_ASSIGNEE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.WAITING_FOR_COMPLETE_NODE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_CANDIDATE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_ as NODE_CREATION_DATE_, P.PROC_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.BIZ_DESC_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_ as PROC_CREATION_DATE_, PD.PROC_DEF_ID_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_, PD.VERSION_, PD.PROC_DEF_STATUS_
  from FF_TASK T
 inner join FF_NODE N
    on N.NODE_ID_ = T.NODE_ID_
 inner join FF_PROC P
    on P.PROC_ID_ = N.PROC_ID_
 inner join FF_PROC_DEF PD
    on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_;
