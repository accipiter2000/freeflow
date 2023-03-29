prompt PL/SQL Developer Export User Objects for user FF@FFOA
prompt Created by Administrator on 2023��3��29��
set define off
spool 1.log

prompt
prompt Creating table FF_PROC_DEF
prompt ==========================
prompt
create table FF_PROC_DEF
(
  proc_def_id_                  VARCHAR2(40) not null,
  proc_def_code_                VARCHAR2(60) not null,
  proc_def_name_                VARCHAR2(60) not null,
  proc_def_cat_                 VARCHAR2(100),
  proc_def_model_               CLOB not null,
  proc_def_diagram_file_        BLOB,
  proc_def_diagram_file_name_   VARCHAR2(300),
  proc_def_diagram_file_length_ INTEGER,
  proc_def_diagram_width_       INTEGER,
  proc_def_diagram_height_      INTEGER,
  memo_                         VARCHAR2(300),
  version_                      INTEGER not null,
  proc_def_status_              VARCHAR2(20) not null,
  creation_date_                TIMESTAMP(6),
  update_date_                  TIMESTAMP(6),
  operator_id_                  VARCHAR2(40),
  operator_name_                VARCHAR2(60)
)
;
comment on table FF_PROC_DEF
  is '���̶���';
comment on column FF_PROC_DEF.proc_def_id_
  is '���̶���ID';
comment on column FF_PROC_DEF.proc_def_code_
  is '���̶������';
comment on column FF_PROC_DEF.proc_def_name_
  is '���̶�������';
comment on column FF_PROC_DEF.proc_def_cat_
  is '���̶������';
comment on column FF_PROC_DEF.proc_def_model_
  is '���̶���ģ��';
comment on column FF_PROC_DEF.proc_def_diagram_file_
  is '���̶���ͼ�ļ�';
comment on column FF_PROC_DEF.proc_def_diagram_file_name_
  is '���̶���ͼ�ļ�����';
comment on column FF_PROC_DEF.proc_def_diagram_file_length_
  is '���̶���ͼ�ļ�����';
comment on column FF_PROC_DEF.proc_def_diagram_width_
  is '���̶���ͼ���';
comment on column FF_PROC_DEF.proc_def_diagram_height_
  is '���̶���ͼ�߶�';
comment on column FF_PROC_DEF.memo_
  is '��ע';
comment on column FF_PROC_DEF.version_
  is '�汾';
comment on column FF_PROC_DEF.proc_def_status_
  is '���̶���״̬';
comment on column FF_PROC_DEF.creation_date_
  is '��������';
comment on column FF_PROC_DEF.update_date_
  is '��������';
comment on column FF_PROC_DEF.operator_id_
  is '������ԱID';
comment on column FF_PROC_DEF.operator_name_
  is '������Ա����';
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
  adjust_proc_def_id_           VARCHAR2(40) not null,
  proc_def_id_                  VARCHAR2(40) not null,
  proc_def_model_               CLOB not null,
  proc_def_diagram_file_        BLOB,
  proc_def_diagram_file_name_   VARCHAR2(300),
  proc_def_diagram_file_length_ INTEGER,
  proc_def_diagram_width_       INTEGER,
  proc_def_diagram_height_      INTEGER,
  creation_date_                TIMESTAMP(6),
  update_date_                  TIMESTAMP(6),
  operator_id_                  VARCHAR2(40),
  operator_name_                VARCHAR2(60)
)
;
comment on table FF_ADJUST_PROC_DEF
  is '�������̶���';
comment on column FF_ADJUST_PROC_DEF.adjust_proc_def_id_
  is '�������̶���ID';
comment on column FF_ADJUST_PROC_DEF.proc_def_id_
  is '���̶���ID';
comment on column FF_ADJUST_PROC_DEF.proc_def_model_
  is '���̶���ģ��';
comment on column FF_ADJUST_PROC_DEF.proc_def_diagram_file_
  is '���̶���ͼ�ļ�';
comment on column FF_ADJUST_PROC_DEF.proc_def_diagram_file_name_
  is '���̶���ͼ�ļ�����';
comment on column FF_ADJUST_PROC_DEF.proc_def_diagram_file_length_
  is '���̶���ͼ�ļ�����';
comment on column FF_ADJUST_PROC_DEF.proc_def_diagram_width_
  is '���̶���ͼ���';
comment on column FF_ADJUST_PROC_DEF.proc_def_diagram_height_
  is '���̶���ͼ�߶�';
comment on column FF_ADJUST_PROC_DEF.creation_date_
  is '��������';
comment on column FF_ADJUST_PROC_DEF.update_date_
  is '��������';
comment on column FF_ADJUST_PROC_DEF.operator_id_
  is '������ԱID';
comment on column FF_ADJUST_PROC_DEF.operator_name_
  is '������Ա����';
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
  delegate_id_    VARCHAR2(40) not null,
  assignee_       VARCHAR2(60),
  assignee_name_  VARCHAR2(60),
  delegator_      VARCHAR2(60) not null,
  delegator_name_ VARCHAR2(60),
  start_date_     TIMESTAMP(6),
  end_date_       TIMESTAMP(6)
)
;
comment on table FF_DELEGATE
  is '����';
comment on column FF_DELEGATE.delegate_id_
  is '����ID';
comment on column FF_DELEGATE.assignee_
  is '������';
comment on column FF_DELEGATE.assignee_name_
  is '����������';
comment on column FF_DELEGATE.delegator_
  is '������';
comment on column FF_DELEGATE.delegator_name_
  is '����������';
comment on column FF_DELEGATE.start_date_
  is '��ʼ����';
comment on column FF_DELEGATE.end_date_
  is '��������';
alter table FF_DELEGATE
  add constraint PK_FF_DELEGATE primary key (DELEGATE_ID_);

prompt
prompt Creating table FF_NODE
prompt ======================
prompt
create table FF_NODE
(
  node_id_                      VARCHAR2(40) not null,
  parent_node_id_               VARCHAR2(40),
  proc_id_                      VARCHAR2(40) not null,
  previous_node_ids_            VARCHAR2(280),
  last_complete_node_ids_       VARCHAR2(280),
  sub_proc_def_id_              VARCHAR2(40),
  adjust_sub_proc_def_id_       VARCHAR2(40),
  node_type_                    VARCHAR2(20) not null,
  node_code_                    VARCHAR2(60),
  node_name_                    VARCHAR2(60),
  parent_node_code_             VARCHAR2(100),
  assignee_                     VARCHAR2(500),
  candidate_                    VARCHAR2(500),
  action_                       VARCHAR2(500),
  due_date_                     VARCHAR2(200),
  complete_expression_          VARCHAR2(200),
  complete_return_              VARCHAR2(20) not null,
  exclusive_                    VARCHAR2(20) not null,
  forwardable_                  VARCHAR2(20) not null,
  auto_complete_same_assignee_  VARCHAR2(20) not null,
  auto_complete_empty_assignee_ VARCHAR2(20) not null,
  inform_                       VARCHAR2(20) not null,
  priority_                     INTEGER not null,
  node_end_user_                VARCHAR2(40),
  node_end_user_name_           VARCHAR2(60),
  node_end_date_                TIMESTAMP(6),
  isolate_sub_proc_def_code_    VARCHAR2(60),
  isolate_sub_proc_status_      VARCHAR2(60),
  node_status_                  VARCHAR2(20) not null,
  creation_date_                TIMESTAMP(6) not null
)
;
comment on table FF_NODE
  is '�ڵ�';
comment on column FF_NODE.node_id_
  is '�ڵ�ID';
comment on column FF_NODE.parent_node_id_
  is '�ϼ��ڵ�ID';
comment on column FF_NODE.proc_id_
  is '����ID';
comment on column FF_NODE.previous_node_ids_
  is 'ǰ�ڵ�IDs';
comment on column FF_NODE.last_complete_node_ids_
  is '�����ɽڵ�IDs';
comment on column FF_NODE.sub_proc_def_id_
  is '�����̶���ID';
comment on column FF_NODE.adjust_sub_proc_def_id_
  is '���������̶���ID';
comment on column FF_NODE.node_type_
  is '�ڵ�����';
comment on column FF_NODE.node_code_
  is '�ڵ����';
comment on column FF_NODE.node_name_
  is '�ڵ�����';
comment on column FF_NODE.parent_node_code_
  is '�ϼ��ڵ����';
comment on column FF_NODE.assignee_
  is '������';
comment on column FF_NODE.candidate_
  is '��ѡ��';
comment on column FF_NODE.action_
  is 'ҵ����Ϊ';
comment on column FF_NODE.due_date_
  is '��ֹ����';
comment on column FF_NODE.complete_expression_
  is '��ɱ��ʽ';
comment on column FF_NODE.complete_return_
  is '��ɺ󷵻�ǰһ���ڵ�';
comment on column FF_NODE.exclusive_
  is '����';
comment on column FF_NODE.forwardable_
  is '��ת��';
comment on column FF_NODE.auto_complete_same_assignee_
  is '�Զ������ͬ����������';
comment on column FF_NODE.auto_complete_empty_assignee_
  is '�Զ���ɿսڵ�';
comment on column FF_NODE.inform_
  is '֪ͨ';
comment on column FF_NODE.priority_
  is '���ȼ�';
comment on column FF_NODE.node_end_user_
  is '�ڵ������Ա';
comment on column FF_NODE.node_end_user_name_
  is '�ڵ������Ա����';
comment on column FF_NODE.node_end_date_
  is '�ڵ��������';
comment on column FF_NODE.isolate_sub_proc_def_code_
  is '���������̶������';
comment on column FF_NODE.isolate_sub_proc_status_
  is '����������״̬';
comment on column FF_NODE.node_status_
  is '�ڵ�״̬';
comment on column FF_NODE.creation_date_
  is '��������';
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
prompt Creating table FF_PROC
prompt ======================
prompt
create table FF_PROC
(
  proc_id_                  VARCHAR2(40) not null,
  proc_def_id_              VARCHAR2(40) not null,
  adjust_proc_def_id_       VARCHAR2(40),
  isolate_sub_proc_node_id_ VARCHAR2(40),
  biz_id_                   VARCHAR2(40),
  biz_type_                 VARCHAR2(20),
  biz_code_                 VARCHAR2(100),
  biz_name_                 VARCHAR2(100),
  proc_start_user_          VARCHAR2(40),
  proc_start_user_name_     VARCHAR2(60),
  proc_start_date_          TIMESTAMP(6),
  proc_end_user_            VARCHAR2(40),
  proc_end_user_name_       VARCHAR2(60),
  proc_end_date_            TIMESTAMP(6),
  proc_status_              VARCHAR2(20) not null,
  creation_date_            TIMESTAMP(6) not null
)
;
comment on table FF_PROC
  is '����';
comment on column FF_PROC.proc_id_
  is '����ID';
comment on column FF_PROC.proc_def_id_
  is '���̶���ID';
comment on column FF_PROC.adjust_proc_def_id_
  is '�������̶���ID';
comment on column FF_PROC.isolate_sub_proc_node_id_
  is '���������������ڵ�ID';
comment on column FF_PROC.biz_id_
  is 'ҵ������';
comment on column FF_PROC.biz_type_
  is 'ҵ������';
comment on column FF_PROC.biz_code_
  is 'ҵ�����';
comment on column FF_PROC.biz_name_
  is 'ҵ������';
comment on column FF_PROC.proc_start_user_
  is '���̿�ʼ��Ա';
comment on column FF_PROC.proc_start_user_name_
  is '���̿�ʼ��Ա����';
comment on column FF_PROC.proc_start_date_
  is '���̿�ʼ����';
comment on column FF_PROC.proc_end_user_
  is '���������Ա';
comment on column FF_PROC.proc_end_user_name_
  is '���������Ա����';
comment on column FF_PROC.proc_end_date_
  is '�����������';
comment on column FF_PROC.proc_status_
  is '����״̬';
comment on column FF_PROC.creation_date_
  is '��������';
create index FK_PROC_DEF on FF_PROC (PROC_DEF_ID_);
alter table FF_PROC
  add constraint PK_FF_PROC primary key (PROC_ID_);
alter table FF_PROC
  add constraint FK_FF_PROC_ISOLATE_NODE foreign key (ISOLATE_SUB_PROC_NODE_ID_)
  references FF_NODE (NODE_ID_)
  disable
  novalidate;
alter table FF_PROC
  add constraint FK_FF_PROC_PROC_DEF foreign key (PROC_DEF_ID_)
  references FF_PROC_DEF (PROC_DEF_ID_);

prompt
prompt Creating table FF_OPERATION
prompt ===========================
prompt
create table FF_OPERATION
(
  operation_id_     VARCHAR2(40) not null,
  operation_        VARCHAR2(40),
  proc_id_          VARCHAR2(40),
  node_id_          VARCHAR2(40),
  task_id_          VARCHAR2(40),
  memo_             VARCHAR2(1000),
  operator_         VARCHAR2(40),
  operator_name_    VARCHAR2(60),
  operation_date_   TIMESTAMP(6) not null,
  operation_status_ VARCHAR2(20) not null
)
;
comment on table FF_OPERATION
  is '����';
comment on column FF_OPERATION.operation_id_
  is '����ID';
comment on column FF_OPERATION.operation_
  is '����';
comment on column FF_OPERATION.proc_id_
  is '����ID';
comment on column FF_OPERATION.node_id_
  is '�ڵ�ID';
comment on column FF_OPERATION.task_id_
  is '����ID';
comment on column FF_OPERATION.memo_
  is '��ע';
comment on column FF_OPERATION.operator_
  is '������';
comment on column FF_OPERATION.operator_name_
  is '����������';
comment on column FF_OPERATION.operation_date_
  is '��������';
comment on column FF_OPERATION.operation_status_
  is '����״̬';
alter table FF_OPERATION
  add constraint FF_FF_OPERATION primary key (OPERATION_ID_);

prompt
prompt Creating table FF_NODE_OP
prompt =========================
prompt
create table FF_NODE_OP
(
  node_op_id_                   VARCHAR2(40) not null,
  operation_id_                 VARCHAR2(40),
  operation_type_               VARCHAR2(20),
  operation_order_              INTEGER,
  operation_date_               TIMESTAMP(6),
  operation_status_             VARCHAR2(20),
  node_id_                      VARCHAR2(40) not null,
  parent_node_id_               VARCHAR2(40),
  proc_id_                      VARCHAR2(40),
  previous_node_ids_            VARCHAR2(280),
  last_complete_node_ids_       VARCHAR2(280),
  sub_proc_def_id_              VARCHAR2(40),
  adjust_sub_proc_def_id_       VARCHAR2(40),
  node_type_                    VARCHAR2(20),
  node_code_                    VARCHAR2(60),
  node_name_                    VARCHAR2(60),
  parent_node_code_             VARCHAR2(60),
  assignee_                     VARCHAR2(500),
  candidate_                    VARCHAR2(500),
  action_                       VARCHAR2(500),
  due_date_                     VARCHAR2(200),
  complete_expression_          VARCHAR2(200),
  complete_return_              VARCHAR2(20),
  exclusive_                    VARCHAR2(20),
  forwardable_                  VARCHAR2(20),
  auto_complete_same_assignee_  VARCHAR2(20),
  auto_complete_empty_assignee_ VARCHAR2(20),
  inform_                       VARCHAR2(20),
  priority_                     INTEGER,
  node_end_user_                VARCHAR2(40),
  node_end_user_name_           VARCHAR2(60),
  node_end_date_                TIMESTAMP(6),
  isolate_sub_proc_def_code_    VARCHAR2(60),
  isolate_sub_proc_status_      VARCHAR2(60),
  node_status_                  VARCHAR2(20),
  creation_date_                TIMESTAMP(6)
)
;
comment on table FF_NODE_OP
  is '�ڵ����';
comment on column FF_NODE_OP.node_op_id_
  is '�ڵ����ID';
comment on column FF_NODE_OP.operation_id_
  is '����ID';
comment on column FF_NODE_OP.operation_type_
  is '��������';
comment on column FF_NODE_OP.operation_order_
  is '����˳��';
comment on column FF_NODE_OP.operation_date_
  is '��������';
comment on column FF_NODE_OP.operation_status_
  is '����״̬';
comment on column FF_NODE_OP.node_id_
  is '�ڵ�ID';
comment on column FF_NODE_OP.parent_node_id_
  is '�ϼ��ڵ�ID';
comment on column FF_NODE_OP.proc_id_
  is '����ID';
comment on column FF_NODE_OP.previous_node_ids_
  is 'ǰ�ڵ�IDs';
comment on column FF_NODE_OP.last_complete_node_ids_
  is '�����ɽڵ�IDs';
comment on column FF_NODE_OP.sub_proc_def_id_
  is '�����̶���ID';
comment on column FF_NODE_OP.adjust_sub_proc_def_id_
  is '���������̶���ID';
comment on column FF_NODE_OP.node_type_
  is '�ڵ�����';
comment on column FF_NODE_OP.node_code_
  is '�ڵ����';
comment on column FF_NODE_OP.node_name_
  is '�ڵ�����';
comment on column FF_NODE_OP.parent_node_code_
  is '�ϼ��ڵ����';
comment on column FF_NODE_OP.assignee_
  is '������';
comment on column FF_NODE_OP.candidate_
  is '��ѡ��';
comment on column FF_NODE_OP.action_
  is 'ҵ����Ϊ';
comment on column FF_NODE_OP.due_date_
  is '��ֹ����';
comment on column FF_NODE_OP.complete_expression_
  is '��ɱ��ʽ';
comment on column FF_NODE_OP.complete_return_
  is '��ɺ󷵻�ǰһ���ڵ�';
comment on column FF_NODE_OP.exclusive_
  is '����';
comment on column FF_NODE_OP.forwardable_
  is '��ת��';
comment on column FF_NODE_OP.auto_complete_same_assignee_
  is '�Զ������ͬ����������';
comment on column FF_NODE_OP.auto_complete_empty_assignee_
  is '�Զ����û�а����˽ڵ�';
comment on column FF_NODE_OP.inform_
  is '֪ͨ';
comment on column FF_NODE_OP.priority_
  is '���ȼ�';
comment on column FF_NODE_OP.node_end_user_
  is '�ڵ������Ա';
comment on column FF_NODE_OP.node_end_user_name_
  is '�ڵ������Ա����';
comment on column FF_NODE_OP.node_end_date_
  is '�ڵ��������';
comment on column FF_NODE_OP.isolate_sub_proc_def_code_
  is '���������̶������';
comment on column FF_NODE_OP.isolate_sub_proc_status_
  is '����������״̬';
comment on column FF_NODE_OP.node_status_
  is '�ڵ�״̬';
comment on column FF_NODE_OP.creation_date_
  is '��������';
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
  node_var_id_   VARCHAR2(40) not null,
  node_id_       VARCHAR2(40) not null,
  var_type_      VARCHAR2(20) not null,
  var_name_      VARCHAR2(60) not null,
  value_         VARCHAR2(3000),
  obj_           BLOB,
  creation_date_ TIMESTAMP(6) not null
)
;
comment on table FF_NODE_VAR
  is '�ڵ����';
comment on column FF_NODE_VAR.node_var_id_
  is '�ڵ����ID';
comment on column FF_NODE_VAR.node_id_
  is '�ڵ�ID';
comment on column FF_NODE_VAR.var_type_
  is '��������';
comment on column FF_NODE_VAR.var_name_
  is '��������';
comment on column FF_NODE_VAR.value_
  is 'ֵ';
comment on column FF_NODE_VAR.obj_
  is '����';
comment on column FF_NODE_VAR.creation_date_
  is '��������';
create index IX_SUB_PROC_VAR_NAME on FF_NODE_VAR (VAR_NAME_);
create index IX_SUB_PROC_VAR_VALUE on FF_NODE_VAR (VALUE_);
alter table FF_NODE_VAR
  add constraint PK_FF_NODE_VAR primary key (NODE_VAR_ID_);
alter table FF_NODE_VAR
  add constraint FK_FF_NODE_VAR_NODE foreign key (NODE_ID_)
  references FF_NODE (NODE_ID_);

prompt
prompt Creating table FF_NODE_VAR_OP
prompt =============================
prompt
create table FF_NODE_VAR_OP
(
  node_var_op_id_   VARCHAR2(40) not null,
  operation_id_     VARCHAR2(40),
  operation_type_   VARCHAR2(20),
  operation_order_  INTEGER,
  operation_date_   TIMESTAMP(6),
  operation_status_ VARCHAR2(20),
  node_var_id_      VARCHAR2(40) not null,
  node_id_          VARCHAR2(40),
  var_type_         VARCHAR2(20),
  var_name_         VARCHAR2(60),
  value_            VARCHAR2(3000),
  obj_              BLOB,
  creation_date_    TIMESTAMP(6)
)
;
comment on table FF_NODE_VAR_OP
  is '�ڵ��������';
comment on column FF_NODE_VAR_OP.node_var_op_id_
  is '�ڵ��������ID';
comment on column FF_NODE_VAR_OP.operation_id_
  is '����ID';
comment on column FF_NODE_VAR_OP.operation_type_
  is '��������';
comment on column FF_NODE_VAR_OP.operation_order_
  is '����˳��';
comment on column FF_NODE_VAR_OP.operation_date_
  is '��������';
comment on column FF_NODE_VAR_OP.operation_status_
  is '����״̬';
comment on column FF_NODE_VAR_OP.node_var_id_
  is '�ڵ����ID';
comment on column FF_NODE_VAR_OP.node_id_
  is '�ڵ�ID';
comment on column FF_NODE_VAR_OP.var_type_
  is '��������';
comment on column FF_NODE_VAR_OP.var_name_
  is '��������';
comment on column FF_NODE_VAR_OP.value_
  is 'ֵ';
comment on column FF_NODE_VAR_OP.obj_
  is '����';
comment on column FF_NODE_VAR_OP.creation_date_
  is '��������';
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
  operation_follow_up_id_ VARCHAR2(40) not null,
  operation_id_           VARCHAR2(40) not null,
  follow_up_operation_id_ VARCHAR2(40) not null,
  operation_date_         TIMESTAMP(6) not null
)
;
comment on table FF_OPERATION_FOLLOW_UP
  is '��������';
comment on column FF_OPERATION_FOLLOW_UP.operation_follow_up_id_
  is '��������ID';
comment on column FF_OPERATION_FOLLOW_UP.operation_id_
  is '����ID';
comment on column FF_OPERATION_FOLLOW_UP.follow_up_operation_id_
  is '��������ID';
comment on column FF_OPERATION_FOLLOW_UP.operation_date_
  is '��������';
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
  proc_op_id_               VARCHAR2(40) not null,
  operation_id_             VARCHAR2(40),
  operation_type_           VARCHAR2(20),
  operation_order_          INTEGER,
  operation_date_           TIMESTAMP(6),
  operation_status_         VARCHAR2(20),
  proc_id_                  VARCHAR2(40) not null,
  proc_def_id_              VARCHAR2(40),
  adjust_proc_def_id_       VARCHAR2(40),
  isolate_sub_proc_node_id_ VARCHAR2(40),
  biz_id_                   VARCHAR2(40),
  biz_type_                 VARCHAR2(20),
  biz_code_                 VARCHAR2(100),
  biz_name_                 VARCHAR2(100),
  proc_start_user_          VARCHAR2(40),
  proc_start_user_name_     VARCHAR2(60),
  proc_start_date_          TIMESTAMP(6),
  proc_end_user_            VARCHAR2(40),
  proc_end_user_name_       VARCHAR2(60),
  proc_end_date_            TIMESTAMP(6),
  proc_status_              VARCHAR2(20),
  creation_date_            TIMESTAMP(6)
)
;
comment on table FF_PROC_OP
  is '���̲���';
comment on column FF_PROC_OP.proc_op_id_
  is '���̲���ID';
comment on column FF_PROC_OP.operation_id_
  is '����ID';
comment on column FF_PROC_OP.operation_type_
  is '��������';
comment on column FF_PROC_OP.operation_order_
  is '����˳��';
comment on column FF_PROC_OP.operation_date_
  is '��������';
comment on column FF_PROC_OP.operation_status_
  is '����״̬';
comment on column FF_PROC_OP.proc_id_
  is '����ID';
comment on column FF_PROC_OP.proc_def_id_
  is '���̶���ID';
comment on column FF_PROC_OP.adjust_proc_def_id_
  is '�������̶���ID';
comment on column FF_PROC_OP.isolate_sub_proc_node_id_
  is '���������������ڵ�ID';
comment on column FF_PROC_OP.biz_id_
  is 'ҵ������';
comment on column FF_PROC_OP.biz_type_
  is 'ҵ������';
comment on column FF_PROC_OP.biz_code_
  is 'ҵ�����';
comment on column FF_PROC_OP.biz_name_
  is 'ҵ������';
comment on column FF_PROC_OP.proc_start_user_
  is '���̿�ʼ��Ա';
comment on column FF_PROC_OP.proc_start_user_name_
  is '���̿�ʼ��Ա����';
comment on column FF_PROC_OP.proc_start_date_
  is '���̿�ʼ����';
comment on column FF_PROC_OP.proc_end_user_
  is '���������Ա';
comment on column FF_PROC_OP.proc_end_user_name_
  is '���������Ա����';
comment on column FF_PROC_OP.proc_end_date_
  is '�����������';
comment on column FF_PROC_OP.proc_status_
  is '����״̬';
comment on column FF_PROC_OP.creation_date_
  is '��������';
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
  task_id_          VARCHAR2(40) not null,
  node_id_          VARCHAR2(40),
  previous_task_id_ VARCHAR2(40),
  task_type_        VARCHAR2(20) not null,
  assignee_         VARCHAR2(40),
  assignee_name_    VARCHAR2(60),
  executor_         VARCHAR2(40),
  executor_name_    VARCHAR2(60),
  action_           VARCHAR2(600),
  claim_date_       TIMESTAMP(6),
  due_date_         TIMESTAMP(6),
  complete_date_    TIMESTAMP(6),
  priority_         INTEGER not null,
  forwardable_      VARCHAR2(20) not null,
  forward_status_   VARCHAR2(20) not null,
  task_status_      VARCHAR2(20) not null,
  creation_date_    TIMESTAMP(6) not null
)
;
comment on table FF_TASK
  is '����';
comment on column FF_TASK.task_id_
  is '����ID';
comment on column FF_TASK.node_id_
  is '�ڵ�ID';
comment on column FF_TASK.previous_task_id_
  is 'ǰһ������ID';
comment on column FF_TASK.task_type_
  is '��������';
comment on column FF_TASK.assignee_
  is '������';
comment on column FF_TASK.assignee_name_
  is '����������';
comment on column FF_TASK.executor_
  is 'ִ����';
comment on column FF_TASK.executor_name_
  is 'ִ��������';
comment on column FF_TASK.action_
  is 'ҵ����Ϊ';
comment on column FF_TASK.claim_date_
  is '��������';
comment on column FF_TASK.due_date_
  is '��ֹ����';
comment on column FF_TASK.complete_date_
  is '�������';
comment on column FF_TASK.priority_
  is '���ȼ�';
comment on column FF_TASK.forwardable_
  is '��ת��';
comment on column FF_TASK.forward_status_
  is 'ת��״̬';
comment on column FF_TASK.task_status_
  is '����״̬';
comment on column FF_TASK.creation_date_
  is '��������';
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
  task_op_id_       VARCHAR2(40) not null,
  operation_id_     VARCHAR2(40),
  operation_type_   VARCHAR2(20),
  operation_order_  INTEGER,
  operation_date_   TIMESTAMP(6),
  operation_status_ VARCHAR2(20),
  task_id_          VARCHAR2(40) not null,
  node_id_          VARCHAR2(40),
  previous_task_id_ VARCHAR2(40),
  task_type_        VARCHAR2(20),
  assignee_         VARCHAR2(40),
  assignee_name_    VARCHAR2(60),
  executor_         VARCHAR2(40),
  executor_name_    VARCHAR2(60),
  action_           VARCHAR2(600),
  claim_date_       TIMESTAMP(6),
  due_date_         TIMESTAMP(6),
  complete_date_    TIMESTAMP(6),
  priority_         INTEGER,
  forwardable_      VARCHAR2(20),
  forward_status_   VARCHAR2(20),
  task_status_      VARCHAR2(20),
  creation_date_    TIMESTAMP(6)
)
;
comment on table FF_TASK_OP
  is '�������';
comment on column FF_TASK_OP.task_op_id_
  is '�������ID';
comment on column FF_TASK_OP.operation_id_
  is '����ID';
comment on column FF_TASK_OP.operation_type_
  is '��������';
comment on column FF_TASK_OP.operation_order_
  is '����˳��';
comment on column FF_TASK_OP.operation_date_
  is '��������';
comment on column FF_TASK_OP.operation_status_
  is '����״̬';
comment on column FF_TASK_OP.task_id_
  is '����ID';
comment on column FF_TASK_OP.node_id_
  is '�ڵ�ID';
comment on column FF_TASK_OP.previous_task_id_
  is 'ǰһ������ID';
comment on column FF_TASK_OP.task_type_
  is '��������';
comment on column FF_TASK_OP.assignee_
  is '������';
comment on column FF_TASK_OP.assignee_name_
  is '����������';
comment on column FF_TASK_OP.executor_
  is 'ִ����';
comment on column FF_TASK_OP.executor_name_
  is 'ִ��������';
comment on column FF_TASK_OP.action_
  is 'ҵ����Ϊ';
comment on column FF_TASK_OP.claim_date_
  is '��������';
comment on column FF_TASK_OP.due_date_
  is '��ֹ����';
comment on column FF_TASK_OP.complete_date_
  is '�������';
comment on column FF_TASK_OP.priority_
  is '���ȼ�';
comment on column FF_TASK_OP.forwardable_
  is '��ת��';
comment on column FF_TASK_OP.forward_status_
  is 'ת��״̬';
comment on column FF_TASK_OP.task_status_
  is '����״̬';
comment on column FF_TASK_OP.creation_date_
  is '��������';
alter table FF_TASK_OP
  add constraint PK_FF_TASK_OP primary key (TASK_OP_ID_);
alter table FF_TASK_OP
  add constraint FK_FF_TASK_OP_OPERATION foreign key (OPERATION_ID_)
  references FF_OPERATION (OPERATION_ID_);

prompt
prompt Creating view FFV_ADJUST_PROC_DEF
prompt =================================
prompt
create or replace force view ffv_adjust_proc_def as
select APD.ADJUST_PROC_DEF_ID_, APD.PROC_DEF_ID_, APD.PROC_DEF_MODEL_, APD.PROC_DEF_DIAGRAM_FILE_, APD.PROC_DEF_DIAGRAM_FILE_NAME_, APD.PROC_DEF_DIAGRAM_FILE_LENGTH_, APD.PROC_DEF_DIAGRAM_WIDTH_, APD.PROC_DEF_DIAGRAM_HEIGHT_, APD.CREATION_DATE_, APD.UPDATE_DATE_, APD.OPERATOR_ID_, APD.OPERATOR_NAME_ from FF_ADJUST_PROC_DEF APD;

prompt
prompt Creating view FFV_DELEGATE
prompt ==========================
prompt
create or replace force view ffv_delegate as
select D.DELEGATE_ID_, D.ASSIGNEE_, D.ASSIGNEE_NAME_, D.DELEGATOR_, D.DELEGATOR_NAME_, D.START_DATE_, D.END_DATE_ from FF_DELEGATE D;

prompt
prompt Creating view FFV_NODE
prompt ======================
prompt
create or replace force view ffv_node as
select N.NODE_ID_, N.PARENT_NODE_ID_, N.PROC_ID_, N.PREVIOUS_NODE_IDS_, N.LAST_COMPLETE_NODE_IDS_, N.SUB_PROC_DEF_ID_, N.ADJUST_SUB_PROC_DEF_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.PARENT_NODE_CODE_, N.ASSIGNEE_, N.CANDIDATE_, N.ACTION_, N.DUE_DATE_, N.COMPLETE_EXPRESSION_, N.COMPLETE_RETURN_, N.EXCLUSIVE_, N.FORWARDABLE_, N.AUTO_COMPLETE_SAME_ASSIGNEE_, N.AUTO_COMPLETE_EMPTY_ASSIGNEE_, N.INFORM_, N.PRIORITY_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.ISOLATE_SUB_PROC_DEF_CODE_, N.ISOLATE_SUB_PROC_STATUS_, N.NODE_STATUS_, N.CREATION_DATE_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_START_DATE_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_
  from FF_NODE N
 inner join FF_PROC P
    on P.PROC_ID_ = N.PROC_ID_;

prompt
prompt Creating view FFV_NODE_VAR
prompt ==========================
prompt
create or replace force view ffv_node_var as
select PV.NODE_VAR_ID_, PV.NODE_ID_, PV.VAR_TYPE_, PV.VAR_NAME_, PV.VALUE_, PV.OBJ_, PV.CREATION_DATE_, N.PARENT_NODE_ID_, N.PROC_ID_ from FF_NODE_VAR PV inner join FF_NODE N on N.NODE_ID_ = PV.NODE_ID_;

prompt
prompt Creating view FFV_PROC
prompt ======================
prompt
create or replace force view ffv_proc as
select P.PROC_ID_, P.PROC_DEF_ID_, P.ADJUST_PROC_DEF_ID_, P.ISOLATE_SUB_PROC_NODE_ID_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.PROC_START_USER_, P.PROC_START_USER_NAME_, P.PROC_START_DATE_, P.PROC_END_USER_, P.PROC_END_USER_NAME_, P.PROC_END_DATE_, P.PROC_STATUS_, P.CREATION_DATE_, PD.PROC_DEF_CODE_, PD.PROC_DEF_NAME_, PD.PROC_DEF_CAT_ from FF_PROC P inner join FF_PROC_DEF PD on PD.PROC_DEF_ID_ = P.PROC_DEF_ID_;

prompt
prompt Creating view FFV_OPERATION
prompt ===========================
prompt
create or replace force view ffv_operation as
select O.OPERATION_ID_, O.OPERATION_, O.PROC_ID_, O.NODE_ID_, O.TASK_ID_, O.MEMO_, O.OPERATOR_, O.OPERATOR_NAME_, O.OPERATION_DATE_, O.OPERATION_STATUS_, P.BIZ_ID_, P.BIZ_TYPE_, P.BIZ_CODE_, P.BIZ_NAME_, P.PROC_DEF_ID_, P.PROC_DEF_CODE_, P.PROC_DEF_NAME_, P.PROC_DEF_CAT_ from FF_OPERATION O left outer join FFV_PROC P on P.PROC_ID_ = O.PROC_ID_;

prompt
prompt Creating view FFV_PROC_DEF
prompt ==========================
prompt
create or replace force view ffv_proc_def as
select PROC_DEF_ID_, PROC_DEF_CODE_, PROC_DEF_NAME_, PROC_DEF_CAT_, PROC_DEF_MODEL_, PROC_DEF_DIAGRAM_FILE_, PROC_DEF_DIAGRAM_FILE_NAME_, PROC_DEF_DIAGRAM_FILE_LENGTH_, PROC_DEF_DIAGRAM_WIDTH_, PROC_DEF_DIAGRAM_HEIGHT_, MEMO_, VERSION_, PROC_DEF_STATUS_, CREATION_DATE_, UPDATE_DATE_, OPERATOR_ID_, OPERATOR_NAME_ from FF_PROC_DEF;

prompt
prompt Creating view FFV_TASK
prompt ======================
prompt
create or replace force view ffv_task as
select T.TASK_ID_, T.NODE_ID_, T.PREVIOUS_TASK_ID_, T.TASK_TYPE_, T.ASSIGNEE_, T.ASSIGNEE_NAME_, T.EXECUTOR_, T.EXECUTOR_NAME_, T.ACTION_, T.CLAIM_DATE_, T.DUE_DATE_, T.COMPLETE_DATE_, T.PRIORITY_, T.FORWARDABLE_, T.FORWARD_STATUS_, T.TASK_STATUS_, T.CREATION_DATE_, N.PARENT_NODE_ID_, N.PROC_ID_, N.NODE_TYPE_, N.NODE_CODE_, N.NODE_NAME_, N.EXCLUSIVE_, N.NODE_END_USER_, N.NODE_END_USER_NAME_, N.NODE_END_DATE_, N.NODE_STATUS_, N.BIZ_ID_, N.BIZ_TYPE_, N.BIZ_CODE_, N.BIZ_NAME_, N.PROC_START_USER_, N.PROC_START_USER_NAME_, N.PROC_START_DATE_, N.PROC_END_USER_, N.PROC_END_USER_NAME_, N.PROC_END_DATE_, N.PROC_STATUS_ from FF_TASK T inner join FFV_NODE N on N.NODE_ID_ = T.NODE_ID_;


prompt Done
spool off
set define on
