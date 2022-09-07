<template>
  <div class="app-container">
  <el-card>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="统计项id" prop="countId">
        <el-input
          v-model="queryParams.countId"
          placeholder="请输入统计项id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="销售员id" prop="salesmanId">
        <el-input
          v-model="queryParams.salesmanId"
          placeholder="请输入销售员id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="已完成销售额" prop="completedSales">
        <el-input
          v-model="queryParams.completedSales"
          placeholder="请输入已完成销售额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务销售额" prop="taskSales">
        <el-input
          v-model="queryParams.taskSales"
          placeholder="请输入任务销售额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务进程" prop="progress">
        <el-input
          v-model="queryParams.progress"
          placeholder="请输入任务进程"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
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
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="salescountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="统计项id" align="center" prop="countId" />
      <el-table-column label="销售员id" align="center" prop="salesmanId" />
      <el-table-column label="已完成销售额" align="center" prop="completedSales" />
      <el-table-column label="任务销售额" align="center" prop="taskSales" />
      <el-table-column label="任务进程" align="center" prop="progress" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
</el-card>
    <!-- 添加或修改销售统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="销售员id" prop="salesmanId">
          <el-input v-model="form.salesmanId" placeholder="请输入销售员id" />
        </el-form-item>
        <el-form-item label="已完成销售额" prop="completedSales">
          <el-input v-model="form.completedSales" placeholder="请输入已完成销售额" />
        </el-form-item>
        <el-form-item label="任务销售额" prop="taskSales">
          <el-input v-model="form.taskSales" placeholder="请输入任务销售额" />
        </el-form-item>
        <el-form-item label="任务进程" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入任务进程" />
        </el-form-item>
        <!-- <el-form-item label="删除标签" prop="delLable">
          <el-input v-model="form.delLable" placeholder="请输入删除标签" />
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
import { listSalescount, getSalescount, delSalescount, addSalescount, updateSalescount, exportSalescount } from "@/api/salescount/salescount";

export default {
  name: "Salescount",
  components: {
  },
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
      // 销售统计表格数据
      salescountList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        countId: null,
        salesmanId: null,
        completedSales: null,
        taskSales: null,
        progress: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        salesmanId: [
          { required: true, message: "销售员id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询销售统计列表 */
    getList() {
      this.loading = true;
      listSalescount(this.queryParams).then(response => {
        this.salescountList = response.data.list;
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
        countId: null,
        salesmanId: null,
        completedSales: null,
        taskSales: null,
        progress: null,
        createBy: null,
        createTime: null,
        delLable: null
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
      this.ids = selection.map(item => item.countId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加销售统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const countId = row.countId || this.ids
      getSalescount(countId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改销售统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.countId != null) {
            updateSalescount(this.form).then(response => {
              this.$message("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSalescount(this.form).then(response => {
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
      const countIds = row.countId || this.ids;
      this.$confirm('是否确认删除销售统计编号为"' + countIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSalescount(countIds);
        }).then(() => {
          this.getList();
          this.$message("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有销售统计数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSalescount(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
