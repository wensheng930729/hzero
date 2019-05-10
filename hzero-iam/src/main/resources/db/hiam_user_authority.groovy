package script.db

databaseChangeLog(logicalFilePath: 'script/db/hiam_user_authority.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hiam_user_authority") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hiam_user_authority_s', startValue:"1")
        }
        createTable(tableName: "hiam_user_authority", remarks: "用户权限管理") {
            column(name: "authority_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "user_id", type: "bigint(20)",  remarks: "用户ID，HIAM.HIAM_USER")  {constraints(nullable:"false")}  
            column(name: "tenant_id", type: "bigint(20)",  remarks: "租户ID，HPFM.HPFM_TENANT")  {constraints(nullable:"false")}  
            column(name: "authority_type_code", type: "varchar(" + 30 * weight + ")",  remarks: "权限类型代码，HIAM.USER_AUTHORITY_TYPE_CODE")  {constraints(nullable:"false")}  
            column(name: "include_all_flag", type: "tinyint(1)",   defaultValue:"0",   remarks: "是否包含所有标识")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }
   createIndex(tableName: "hiam_user_authority", indexName: "hiam_user_authority_n1") {
            column(name: "user_id")
            column(name: "authority_type_code")
            column(name: "tenant_id")
        }

    }
}