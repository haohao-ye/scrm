<template>
  <div class="app-container">
  <el-card>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="通知标题" prop="infoTitle">
        <el-input
          v-model="queryParams.infoTitle"
          placeholder="请输入通知标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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

    <el-table v-loading="loading" :data="informList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="通知id" align="center" prop="id" />
      <el-table-column label="通知标题" align="center" prop="infoTitle" />
      <el-table-column label="通知管理" align="center" prop="infoAdm" />
      <el-table-column label="通知销售" align="center" prop="infoSol" />
      <el-table-column label="通知仓管" align="center" prop="infoInv" />
      <el-table-column label="具体内容" align="center" prop="infoBody" />
      <el-table-column label="通知消失的时间" align="center" prop="deadline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
    <!-- 添加或修改inform对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="通知标题" prop="infoTitle">
          <el-input v-model="form.infoTitle" placeholder="请输入通知标题" />
        </el-form-item>
        <el-form-item label="通知管理">
          <el-radio-group v-model="form.infoAdm">
            <el-radio label="否">否</el-radio>
            <el-radio label="是">是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="通知销售">
          <el-radio-group v-model="form.infoSol">
            <el-radio label="否">否</el-radio>
            <el-radio label="是">是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="通知仓管">
          <el-radio-group v-model="form.infoInv">
            <el-radio label="否">否</el-radio>
            <el-radio label="是">是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="具体内容" prop="infoBody">
          <el-input v-model="form.infoBody" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="消失时间" prop="deadline">
          <el-date-picker clearable size="small"
            v-model="form.deadline"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择通知消失的时间">
          </el-date-picker>
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
import { listInform, getInform, delInform, addInform, updateInform, exportInform } from "@/api/Inform/inform";

export default {
  name: "通知",
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
      // inform表格数据
      informList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        infoTitle: null,
        infoBody: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        infoTitle: [
          { required: true, message: "通知标题不能为空", trigger: "blur" }
        ],
        infoAdm: [
          { required: true, message: "通知管理不能为空", trigger: "blur" }
        ],
        infoSol: [
          { required: true, message: "通知销售不能为空", trigger: "blur" }
        ],
        infoInv: [
          { required: true, message: "通知仓管不能为空", trigger: "blur" }
        ],
        infoBody: [
          { required: true, message: "具体内容不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "修改人不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ],
        deadline: [
          { required: true, message: "通知消失的时间不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "删除标签不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询inform列表 */
    getList() {
      this.loading = true;
      listInform(this.queryParams).then(response => {
        this.informList = response.data.list;
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
        infoTitle: null,
        infoAdm: "0",
        infoSol: "0",
        infoInv: "0",
        infoBody: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        deadline: null,
        delFlag: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加通知";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInform(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改inform";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInform(this.form).then(response => {
              this.$message("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInform(this.form).then(response => {
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
      this.$confirm('是否确认删除通知编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInform(ids);
        }).then(() => {
          this.getList();
          this.$message("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有通知数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInform(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

<style lang="scss" scoped>
  @font-face {
    font-family:AliRegular;
    src:url('../../assets/font/Alibaba-PuHuiTi-Regular.ttf')
  }
  @font-face {
    font-family:AliBold;
    src:url('../../assets/font/Alibaba-PuHuiTi-Bold.ttf')
  }
  
  .app-container {
    font-family: "AliRegular","Source Han Sans CN","Microsoft YaHei";
  }
  
  </style>
