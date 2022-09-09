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
        <el-form-item label="名称" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select
            v-model="queryParams.type"
            placeholder="请选择类型"
            clearable
            size="small"
          >
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="价格" prop="price">
          <el-input
            v-model="queryParams.price"
            placeholder="请输入价格"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item> -->
        <el-form-item label="标签" prop="lable">
          <el-input
            v-model="queryParams.lable"
            placeholder="请输入标签"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input
            v-model="queryParams.brand"
            placeholder="请输入品牌"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <!-- <el-form-item label="库存" prop="inventory">
          <el-input
            v-model="queryParams.inventory"
            placeholder="请输入库存"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item> -->
        <!-- <el-form-item label="颜色" prop="color">
          <el-input
            v-model="queryParams.color"
            placeholder="请输入颜色"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item> -->
        <el-form-item label="营销活动" prop="activity_id">
          <el-select v-model="form.activity_id" placeholder="选择活动编号" @click.native="optionSelect">
            <el-option
              v-for="activity in activityList"
              :key="activity.id"
              :value="activity.id"
              :label="activity.name"
             @keyup.enter.native="handleQuery"
            />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="创建时间" prop="creatTime">
          <el-date-picker
            clearable
            size="small"
            v-model="queryParams.creatTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间"
          >
          </el-date-picker>
        </el-form-item> -->
        <!-- <el-form-item label="创建人" prop="creatBy">
          <el-input
            v-model="queryParams.creatBy"
            placeholder="请输入创建人"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item> -->

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
          <el-button
            type="info"
            plain
            icon="el-icon-s-order"
            size="mini"
            @click="handleEntry"
            >订单入库</el-button
          >
        </el-col>
      </el-row>

      <el-table
        v-loading="loading"
        :data="goodsList"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="商品id" align="center" prop="id" />
        <el-table-column label="名称" align="center" prop="name" />
        <el-table-column label="类型" align="center" prop="type" />
        <el-table-column label="价格" align="center" prop="price" />
        <el-table-column label="标签" align="center" prop="lable" />
        <el-table-column label="品牌" align="center" prop="brand" />
        <el-table-column label="库存" align="center" prop="inventory" />
        <el-table-column label="颜色" align="center" prop="color" />
        <el-table-column label="活动" align="center" prop="activity_id" />

        <el-table-column
          label="创建时间"
          align="center"
          prop="creatTime"
          width="180"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.creatTime, "{y}-{m}-{d}") }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建人" align="center" prop="creatBy" />
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
    <!-- 添加或修改商品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option label="请选择字典生成" value="pc" />
          </el-select>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="标签" prop="lable">
          <el-input v-model="form.lable" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="库存" prop="inventory">
          <el-input v-model.number="form.inventory" placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="form.color" placeholder="请输入颜色" />
        </el-form-item>
        <el-form-item label="营销活动" prop="activity_id">
          <el-select v-model="form.activity_id" placeholder="选择活动编号">
            <el-option
              v-for="activity in activityList"
              :key="activity.id"
              :value="activity.id"
              :label="activity.name"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="orderForm" :model="orderForm" :rules="rules1" label-width="80px">
        <el-form-item label="单号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="输入单号" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option label="请选择字典生成" value="pc" />
          </el-select>
        </el-form-item>
        <el-form-item label="总额" prop="totalPrice">
          <el-input v-model="form.price" placeholder="请输入总额" />
        </el-form-item>
        <el-form-item label="标签" prop="lable">
          <el-input v-model="form.lable" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model.number="form.inventory" placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="form.color" placeholder="请输入颜色" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="备注"/>
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
  listGoods,
  getGoods,
  delGoods,
  addGoods,
  updateGoods,
  exportGoods,
  updateByOrder,
} from "@/api/goods/goods";
import { listActivity } from "@/api/activity/activity";
export default {
  name: "Goods",
  components: {},
  data() {
    var checkPrice = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("价格不能为空"));
      }
      setTimeout(() => {
        if (isNaN(value)) {
          callback(new Error("请输入数字值"));
        } else {
          callback();
        }
      }, 100);
    };
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

      // 商品表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        type: null,
        price: null,
        lable: null,
        brand: null,
        inventory: null,
        color: null,
        activity_id: null,
        creatTime: null,
        creatBy: null,
      },
      // 表单参数
      form: {},
      //订单表单参数
      orderForm:{},
      // 表单校验
      rules: {
        name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
        type: [{ required: true, message: "类型不能为空", trigger: "change" }],
        price: [
          {validator:checkPrice, trigger: 'change'}
        ],
        brand: [{ required: true, message: "品牌不能为空", trigger: "blur" }],
        inventory: [
          { required: true, message: "库存不能为空", trigger: "blur" },
        ],
        activity_id: [{ type: "number" }],
      },
      activityList: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品管理列表 */

    getList() {
      this.loading = true;
      listGoods(this.queryParams).then((response) => {
        this.goodsList = response.data.list;
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
        type: null,
        price: null,
        lable: null,
        brand: null,
        inventory: null,
        color: null,
        activity_id: null,
        creatTime: null,
        creatBy: null,
        updateTime: null,
        updateBy: null,
        delFlag: null,
      };
      this.orderForm={
        orderNo: null,
        name: null,
        type: null,
        totalPrice: null,
        lable: null,
        brand: null,
        quantity: null,
        remark:null,
        color: null,
        creatBy: null,
        updateTime: null,
        updateBy: null,
        delFlag: null,
      };
      this.resetForm("form");
      this.resetForm("orderForm");
    },
    test(){
        this.console.log("-----------");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
      this.optionSelect();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // updateSelect() {
    //   this.getList();
    //   this.optionSelect();
    // },
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
      this.title = "添加商品";
      this.optionSelect();
    },
    /**订单入库 */
    handleEntry(){
        this.reset();
        this.open=true;
        this.title="订单";
        
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getGoods(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品";
      });
      this.optionSelect();
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateGoods(this.form).then((response) => {
              this.$message("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then((response) => {
              this.$message("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /**确认订单按钮 */
    submitOrder(){
      this.$refs["orderForm"].validate((valid) => {
        if (valid) {
         
            updateByOrder(this.orderForm).then((response) => {
              this.$message("入库成功");
              this.open = false;
              this.getList();
            });
          
        }
      });

    },
    optionSelect() {
      listActivity(this.queryParams).then((response) => {
        this.activityList = response.data.list;
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;

      this.$confirm('是否确认删除商品编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delGoods(ids);
        })
        .then(() => {
          this.getList();
          this.$message("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;

      this.$confirm("是否确认导出所有商品数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportGoods(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
  @font-face {
    font-family:AliRegular;
    src:url('../../../assets/font/Alibaba-PuHuiTi-Regular.ttf')
  }
  @font-face {
    font-family:AliBold;
    src:url('../../../assets/font/Alibaba-PuHuiTi-Bold.ttf')
  }
  
  .app-container {
    font-family: "AliRegular","Source Han Sans CN","Microsoft YaHei";
  }
  
  </style>