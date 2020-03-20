package com.emsoft.riskwaring.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
//@Component
public class HbaseClient {
    @Autowired
    private HbaseConfig hbaseConfig;

    private static Connection connection = null;
    private static Admin admin = null;

    /**
     * 初始化Hbase连接
     */
    @PostConstruct
    private void init() {
        if (connection != null) {
            return;
        }
        try {
            connection = ConnectionFactory.createConnection(hbaseConfig.configuration());
            admin = connection.getAdmin();
        } catch (IOException e) {
            log.error("HBase create connection failed: {}", e.getMessage());
        }
    }

    /**
     * 创建Hbase表
     *
     * @param tableName      表名
     * @param columnFamilies 列族
     * @throws IOException
     */
    public void createTable(String tableName, String[] columnFamilies) throws IOException {
        TableName name = TableName.valueOf(tableName);
        if (this.tableExists(tableName)) {
            throw new TableExistsException(tableName + "is exists!");
        }
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(name);
        List<ColumnFamilyDescriptor> columnFamilyDescriptorList = new ArrayList<>();
        for (String columnFamily : columnFamilies) {
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily)).build();
            columnFamilyDescriptorList.add(columnFamilyDescriptor);
        }
        tableDescriptorBuilder.setColumnFamilies(columnFamilyDescriptorList);
        TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
        admin.createTable(tableDescriptor);
    }

    /**
     * 插入或者更新
     *
     * @param tableName    表名
     * @param rowKey       rowKey
     * @param columnFamily 列族
     * @param keys         key数组
     * @param values       value数组
     * @throws IOException
     */
    public void insertOrUpdate(String tableName, String rowKey, String columnFamily, String[] keys, String[] values) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        for (int i = 0; i < keys.length; i++) {
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(keys[i]), Bytes.toBytes(values[i]));
            table.put(put);
        }
        table.close();
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 插入或者更新
     *
     * @param tableName    表名
     * @param rowKey       rowKey
     * @param columnFamily 列族
     * @param key          key
     * @param value        value
     * @throws IOException
     */
    public void insertOrUpdate(String tableName, String rowKey, String columnFamily, String key, String value) throws IOException {
        this.insertOrUpdate(tableName, rowKey, columnFamily, new String[]{key}, new String[]{value});
    }

    /**
     * 根据rowKey删除数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @throws IOException
     */
    public void deleteRow(String tableName, String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 删除列族
     *
     * @param tableName    表名
     * @param rowKey       rowKey
     * @param columnFamily 列族
     * @throws IOException
     */
    public void deleteColumnFamily(String tableName, String rowKey, String columnFamily) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addFamily(Bytes.toBytes(columnFamily));
        table.delete(delete);
        table.close();
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 删除某个key
     *
     * @param tableName    表名
     * @param rowKey       rowKey
     * @param columnFamily 列族
     * @param key          key
     * @throws IOException
     */
    public void deleteColumn(String tableName, String rowKey, String columnFamily, String key) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(key));
        table.delete(delete);
        table.close();
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 删除表
     *
     * @param tableName 表名
     * @throws IOException
     */
    public void deleteTable(String tableName) throws IOException {
        if (!this.tableExists(tableName)) {
            return;
        }
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
    }

    /**
     * 获取value
     *
     * @param tableName    表名
     * @param rowKey       rowKey
     * @param columnFamily 列族
     * @param key          key
     * @return
     */
    public String getValue(String tableName, String rowKey, String columnFamily, String key) {
        Table table = null;
        String value = "";
        if (StringUtils.isBlank(tableName) || StringUtils.isBlank(columnFamily)
                || StringUtils.isBlank(rowKey) || StringUtils.isBlank(key)) {
            return null;
        }
        try {
            table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(key));
            Result result = table.get(get);
            List<Cell> cells = result.listCells();
            if (!CollectionUtils.isEmpty(cells)) {
                for (Cell cell : cells) {
                    value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (table != null) {
                    table.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 根据rowKey查询
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @return
     * @throws IOException
     */
    public HashMap selectOneRow(String tableName, String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        HashMap addValMap=getResult(result);
        table.close();
        if (connection != null) {
            connection.close();
        }
        return addValMap;
    }

    /**
     * 根据rowKey过滤，搜索表
     *
     * @param tableName    表名
     * @param rowKeyFilter rowKey
     * @return
     * @throws IOException
     */
    public String scanTable(String tableName, String rowKeyFilter) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        if (StringUtils.isNotBlank(rowKeyFilter)) {
            RowFilter rowFilter = new RowFilter(CompareOperator.EQUAL, new SubstringComparator(rowKeyFilter));
            scan.setFilter(rowFilter);
        }
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            getResult(result);
        }
        scanner.close();
        table.close();
        connection.close();
        return null;
    }


    /**
     * 判断表是否存在
     *
     * @param tableName 表名
     * @return
     * @throws IOException
     */
    public boolean tableExists(String tableName) throws IOException {
        TableName[] tableNames = admin.listTableNames();
        if (tableNames != null && tableNames.length > 0) {
            for (TableName name : tableNames) {
                if (tableName.equals(name.getNameAsString())) {
                    return true;
                }
            }
        }
        return false;
    }

    private HashMap getResult(Result result) {
        String results="";
        HashMap addValMap=new HashMap();
        while (result.advance()) {
            Cell cell = result.current();
            byte[] row = CellUtil.cloneRow(cell);
            byte[] family = CellUtil.cloneFamily(cell);
            byte[] qualifier = CellUtil.cloneQualifier(cell);
            byte[] value = CellUtil.cloneValue(cell);
                results+=String.format("row: %s, family: %s, qualifier: %s, value: %s",
                    new String(row),
                    new String(family),
                    new String(qualifier),
                    new String(value)
            );
                String key= new String(qualifier);
                String val=new String(value);
                addValMap.put(key,value);
        }
        return addValMap;
    }
}
