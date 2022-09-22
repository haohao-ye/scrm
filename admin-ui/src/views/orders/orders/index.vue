<template>
  <div class="app-container">
  <el-card>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户ID" prop="clientId">
        <el-input
          v-model="queryParams.clientId"
          placeholder="请输入客户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名字" prop="clientName">
        <el-input
          v-model="queryParams.clientName"
          placeholder="请输入客户名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品ID" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名字" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="商品量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入商品量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="商品照片" prop="img">
        <el-input
          v-model="queryParams.img"
          placeholder="请输入商品照片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="订单状态" prop="state">
        <el-input
          v-model="queryParams.state"
          placeholder="请输入订单状态"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="销售员ID" prop="salesmanId">
        <el-input
          v-model="queryParams.salesmanId"
          placeholder="请输入销售员ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="总额" prop="totalAmount">
        <el-input
          v-model="queryParams.totalAmount"
          placeholder="请输入总额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除标签" prop="delLable">
        <el-input
          v-model="queryParams.delLable"
          placeholder="请输入删除标签"
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

    <el-table v-loading="loading" :data="ordersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="消费单 ID" align="center" prop="consumptionListId" />
      <el-table-column label="客户 ID" align="center" prop="clientId" />
      <el-table-column label="客户名字" align="center" prop="clientName" />
      <el-table-column label="商品id" align="center" prop="goodsId" />
      <el-table-column label="商品名字" align="center" prop="goodsName" />
      <el-table-column label="商品量" align="center" prop="quantity" />
      <el-table-column label="商品照片" align="center" prop="img" />
      <el-table-column label="订单状态" align="center" prop="state" />
      <el-table-column label="销售员 ID" align="center" prop="salesmanId" />
      <el-table-column label="总额" align="center" prop="totalAmount" />
      <el-table-column label="删除标签" align="center" prop="delLable" />
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
    <!-- 添加或修改订单表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户 ID" prop="clientId">
          <el-input v-model="form.clientId" placeholder="请输入客户id" />
        </el-form-item>
        <el-form-item label="客户名字" prop="clientName">
          <el-input v-model="form.clientName" placeholder="请输入客户名字" />
        </el-form-item>
        <el-form-item label="商品 ID" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品id" />
        </el-form-item>
        <el-form-item label="商品名字" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名字" />
        </el-form-item>
        <el-form-item label="商品量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入商品量" />
        </el-form-item>
        <el-form-item label="商品照片" prop="img">
          <el-input v-model="form.img" placeholder="请输入商品照片" />
        </el-form-item>
        <el-form-item label="订单状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入订单状态" />
        </el-form-item>
        <el-form-item label="销售员 ID" prop="salesmanId">
          <el-input v-model="form.salesmanId" placeholder="请输入销售员id" />
        </el-form-item>
        <el-form-item label="总额" prop="totalAmount">
          <el-input v-model="form.totalAmount" placeholder="请输入总额" />
        </el-form-item>
        <el-form-item label="删除标签" prop="delLable">
          <el-input v-model="form.delLable" placeholder="请输入删除标签" />
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
import { listOrders, getOrders, delOrders, addOrders, updateOrders, exportOrders } from "@/api/orders/orders";

export default {
  name: "Orders",
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
      // 订单表表格数据
      ordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clientId: null,
        clientName: null,
        goodsId: null,
        goodsName: null,
        quantity: null,
        img: null,
        state: null,
        salesmanId: null,
        totalAmount: null,
        delLable: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clientId: [
          { required: true, message: "客户 ID 不能为空", trigger: "blur" }
        ],
        goodsId: [
          { required: true, message: "商品 ID 不能为空", trigger: "blur" }
        ],
        quantity: [
          { required: true, message: "商品量不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "订单状态不能为空", trigger: "blur" }
        ],
        salesmanId: [
          { required: true, message: "销售员 ID 不能为空", trigger: "blur" }
        ],
        totalAmount: [
          { required: true, message: "总额不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询订单表列表 */
    getList() {
      this.loading = true;
      listOrders(this.queryParams).then(response => {
        this.ordersList = response.data.list;
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
        consumptionListId: null,
        clientId: null,
        clientName: null,
        goodsId: null,
        goodsName: null,
        quantity: null,
        img: null,
        state: null,
        salesmanId: null,
        totalAmount: null,
        createTime: null,
        createBy: null,
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
      this.ids = selection.map(item => item.consumptionListId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const consumptionListId = row.consumptionListId || this.ids
      getOrders(consumptionListId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.consumptionListId != null) {
            updateOrders(this.form).then(response => {
              this.$message("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrders(this.form).then(response => {
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
      const consumptionListIds = row.consumptionListId || this.ids;
      this.$confirm('是否确认删除订单表编号为"' + consumptionListIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrders(consumptionListIds);
        }).then(() => {
          this.getList();
          this.$message("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有订单表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrders(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
