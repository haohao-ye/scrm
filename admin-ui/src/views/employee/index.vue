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
        <el-form-item label="名字" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入名字"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="员工部门" prop="deptName">
          <el-select
            v-model="queryParams.deptId"
            placeholder="请选择员工部门"
            clearable
            size="small"
          >
            <el-option
              v-for="item in deptList"
              :key="item.id"
              :label="item.deptName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
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
        <el-col :span="1.5">
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="1"
            :on-exceed="handleExceed"
            :file-list="fileList"
          >
          <el-button
          type="info"
          plain
          icon="el-icon-document"
          size="mini"
          @click="handleImport"
        >导入</el-button> 
          </el-upload>
          <!-- <el-button
          type="info"
          plain
          icon="el-icon-document"
          size="mini"
          
          @click="handleImport"
        >导入</el-button> -->
        </el-col>
      </el-row>

      <el-table
        v-loading="loading"
        :data="employeeList"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="员工工号" align="center" prop="id" />
        <el-table-column label="名字" align="center" prop="name" />
        <el-table-column label="手机号码" align="center" prop="phoneNumber" />
        <el-table-column label="员工部门" align="center" prop="deptName" />
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
            <el-button
              size="mini"
              type="text"
              icon="el-icon-warning-outline"
              @click="resetPw(scope.row)"
              >重置密码</el-button
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
    <!-- 添加或修改员工管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名字" prop="name">
          <el-input v-model="form.name" placeholder="请输入名字" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="身份证" prop="idNum">
          <el-input v-model="form.idNum" placeholder="请输入员工身份证" />
        </el-form-item>
        <el-form-item label="员工部门">
          <el-select v-model="form.deptId" placeholder="请选择员工部门">
            <el-option
              v-for="item in deptList"
              :key="item.id"
              :label="item.deptName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
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
  listEmployee,
  getEmployee,
  delEmployee,
  addEmployee,
  updateEmployee,
  importEmployee,
  resetPassword,
} from "@/api/employee/employee";
import { listDept } from "@/api/dept/dept";
export default {
  name: "Employee",
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
      // 员工管理表格数据
      employeeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        deptId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{ required: true, message: "名字不能为空", trigger: "blur" }],
        idNum: [
          { required: true, message: "员工身份证不能为空", trigger: "blur" },
        ],
        phoneNumber: [
          { required: true, message: "手机号码不能为空", trigger: "blur" },
        ],
        password: [
          { required: true, message: "系统登录密码不能为空", trigger: "blur" },
        ],
        deptName: [
          { required: true, message: "员工部门不能为空", trigger: "blur" },
        ],
      },
      deptList: [],
      importMsg:{ fileName : 'C:\\Users\\Administrator\\Desktop\\test\.xls'},
    };
  },
  created() {
    this.getList();
    this.getDeptList();
  },
  methods: {
    /**查询部门列表 */
    getDeptList() {
      listDept(this.queryParams).then((response) => {
        // console.log(this.deptList);
        this.deptList = response.data.list;
      });
    },
    /** 查询员工管理列表 */
    getList() {
      this.loading = true;
      listEmployee(this.queryParams).then((response) => {
        this.employeeList = response.data.list;
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
        name: null,
        idNum: null,
        phoneNumber: null,
        password: null,
        deptName: "0",
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      // console.log(this.queryParams);
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryParams");
      this.queryParams.deptId = "";
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
      this.title = "添加员工管理";
      this.getDeptList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getEmployee(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工管理";
        this.getDeptList();
      });
    },
    /** 重置密码按钮操作 */
    resetPw(row) {
      const id = row.id || this.id;
      console.log(id);
      this.$confirm('是否确认重置员工管理编号为"' + id + '"的密码?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          getEmployee(id).then((response) => {
            this.form = response.data;
            console.log(this.form);
            resetPassword(this.form);
          });
        })
        .then(() => {
          this.$message("重置成功");
        });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateEmployee(this.form).then((response) => {
              // console.log(this.form);
              this.$message("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmployee(this.form).then((response) => {
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
      this.$confirm(
        '是否确认删除员工管理编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delEmployee(ids);
        })
        .then(() => {
          this.getList();
          this.$message("删除成功");
        });
    },
    /** 导入按钮操作 */
    handleImport() {
      const queryParams = this.queryParams;
      let msg = this.importMsg;
      this.$confirm('是否导入该文件"' , {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        
      })
        .then(function () {
          console.log(msg);
          return importEmployee(msg);
          
        })
        .then((response) => {
          this.getList();
          this.download(response.msg);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
@font-face {
  font-family: AliRegular;
  src: url("../../assets/font/Alibaba-PuHuiTi-Regular.ttf");
}
@font-face {
  font-family: AliBold;
  src: url("../../assets/font/Alibaba-PuHuiTi-Bold.ttf");
}

.app-container {
  font-family: "AliRegular", "Source Han Sans CN", "Microsoft YaHei";
}
</style>
