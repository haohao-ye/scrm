<template>
  <div class="app-container">
    <el-card>
      <el-form
        :model="queryParams"
        ref="queryForm"
        :inline="true"
        v-show="showSearch"
        label-width="68px"
      >
        <el-form-item label="客户姓名" prop="clientName">
          <el-input
            v-model="queryParams.clientName"
            placeholder="请输入客户姓名"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <!-- <el-form-item label="客户地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入客户地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
        <el-form-item label="联系方式" prop="phoneNumber">
          <el-input
            v-model="queryParams.phoneNumber"
            placeholder="请输入联系方式"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <!-- <el-form-item label="客户级别" prop="clientLevel">
        <el-input
          v-model="queryParams.clientLevel"
          placeholder="请输入客户级别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
        <el-form-item label="负责人" prop="clientGroup">
          <el-select
            v-model="queryParams.clientGroup"
            placeholder="请选择负责人"
            clearable
            size="small"
          >
            <el-option
              v-for="item in employeeList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-date-picker
            clearable
            size="small"
            v-model="queryParams.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建人" prop="creator">
          <el-input
            v-model="queryParams.creator"
            placeholder="请输入创建人"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <!-- <el-form-item label="更新人" prop="updatePerson">
        <el-input
          v-model="queryParams.updatePerson"
          placeholder="请输入更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
        <!-- <el-form-item label="删除标签" prop="delLabel">
        <el-input
          v-model="queryParams.delLabel"
          placeholder="请输入删除标签"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> 
      </el-form-item>-->
        <el-form-item>
          <el-button
            type="primary"
            icon="el-icon-search"
            size="mini"
            @click="handleQuery"
            >搜索</el-button
          >
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="mt-15">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            >新增</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            >修改</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            >删除</el-button
          >
        </el-col>
      </el-row>

      <el-table
        v-loading="loading"
        :data="clientList"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="客户id" align="center" prop="id" />
        <el-table-column label="客户姓名" align="center" prop="clientName" />
        <el-table-column label="客户地址" align="center" prop="address" />
        <el-table-column label="联系方式" align="center" prop="phoneNumber" />
        <el-table-column label="客户级别" align="center" prop="clientLevel" />
        <el-table-column label="负责人" align="center" prop="clientGroupName" />
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          width="180"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建人" align="center" prop="creator" />
        <el-table-column label="更新人" align="center" prop="updatePerson" />
        <!-- <el-table-column label="删除标签" align="center" prop="delLabel" /> -->
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>
    <!-- 添加或修改客户表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户姓名" prop="clientName">
          <el-input v-model="form.clientName" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入客户地址" />
        </el-form-item>
        <el-form-item label="联系方式" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="客户级别" prop="clientLevel">
          <el-select v-model="form.clientLevel" placeholder="请选择客户级别">
            <el-option
              v-for="item in levelList"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="负责人" prop="clientGroup">
          <el-select v-model="form.clientGroup" placeholder="请选择负责人">
            <el-option
              v-for="item in employeeList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="创建时间" prop="createTime">
          <el-date-picker clearable size="small"
            v-model="form.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item> -->
        <!-- <el-form-item label="创建人" prop="creator">
          <el-input v-model="form.creator" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="更新人" prop="updatePerson">
          <el-input v-model="form.updatePerson" placeholder="请输入更新人" />
        </el-form-item> -->
        <!-- <el-form-item label="删除标签" prop="delLabel">
          <el-input v-model="form.delLabel" placeholder="请输入删除标签" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listClient,
  getClient,
  delClient,
  addClient,
  updateClient,
  exportClient,
} from "@/api/client/client";
import { listEmp, getEmployee } from "@/api/employee/employee";

export default {
  name: "Client",
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 客户表表格数据
      clientList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clientName: null,
        address: null,
        phoneNumber: null,
        clientLevel: null,
        clientGroup: null,
        createTime: null,
        creator: null,
        updatePerson: null,
        delLabel: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clientName: [
          { required: true, message: "客户名称不能为空", trigger: "blur" },
        ],
        phoneNumber: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
        ],
        clientGroup: [
          { required: true, message: "客户组别不能为空", trigger: "blur" },
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" },
        ],
        delLabel: [
          { required: true, message: "删除标签不能为空", trigger: "blur" },
        ],
      },
      levelList: ["A", "B", "C"],
      employeeList: [],
      employee: "",
    };
  },
  created() {
    this.getList();
    this.getEmpList();
  },
  methods: {
    /** 查询员工表列表 */
    getEmpList() {
      listEmp(this.queryParams).then((response) => {
        this.employeeList = response.data;
      });
    },
    /** 查询客户表列表 */
    getList() {
      this.loading = true;
      listClient(this.queryParams).then((response) => {
        this.clientList = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        clientName: null,
        address: null,
        phoneNumber: null,
        clientLevel: null,
        clientGroup: null,
        createTime: null,
        creator: null,
        updateTime: null,
        updatePerson: null,
        delLabel: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getClient(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateClient(this.form).then((response) => {
              this.$message("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClient(this.form).then((response) => {
              this.$message("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除客户表编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delClient(ids);
        })
        .then(() => {
          this.getList();
          this.$message("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有客户表数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportClient(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        });
    },
  },
};
</script>
