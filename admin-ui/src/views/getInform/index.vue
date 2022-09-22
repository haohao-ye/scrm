<template>
  <div class="app-container">
    <el-card class="mt-15">
      <el-table v-loading="loading" :data="informList">
        <el-table-column label="序号" align="center" prop="id" />
        <el-table-column label="通知标题" align="center" prop="infoTitle" />
        <el-table-column label="具体内容" align="center" prop="infoBody" />
      </el-table>

      <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
        @pagination="getList" />
    </el-card>
  </div>
</template>

<script>
import { listInform, getInform, delInform, addInform, updateInform } from "@/api/Inform/inform";

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
        console.log(response)
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
      this.single = selection.length !== 1
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
      }).then(function () {
        return delInform(ids);
      }).then(() => {
        this.getList();
        this.$message("删除成功");
      })
    },
    // /** 导出按钮操作 */
    // handleExport() {
    //   const queryParams = this.queryParams;
    //   this.$confirm('是否确认导出所有通知数据项?', "警告", {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消",
    //       type: "warning"
    //     }).then(function() {
    //       return exportInform(queryParams);
    //     }).then(response => {
    //       this.download(response.msg);
    //     })
    // }
  }
};
</script>

<style lang="scss" scoped>
@font-face {
  font-family: AliRegular;
  src: url('../../assets/font/Alibaba-PuHuiTi-Regular.ttf')
}

@font-face {
  font-family: AliBold;
  src: url('../../assets/font/Alibaba-PuHuiTi-Bold.ttf')
}

.app-container {
  font-family: "AliRegular", "Source Han Sans CN", "Microsoft YaHei";
}
</style>
