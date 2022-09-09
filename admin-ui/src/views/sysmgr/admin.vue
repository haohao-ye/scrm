<!-- 模板 -->
<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-card>
      <el-form :model="searchForm" :inline="true">
        <el-form-item label="用户名">
          <el-input v-model="searchForm.username" size="small" />
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="searchForm.phoneNumber" size="small" />
        </el-form-item>
        <el-form-item label>
          <el-button size="small" @click="search()">查询</el-button>
          <el-button size="small" @click="reset()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <!-- 列表栏 -->
    <el-card class="mt-15">
      <el-row>
        <el-button size="small" icon="el-icon-plus" @click="add()">添加</el-button>
        <el-button size="small" icon="el-icon-delete" @click="del()">删除</el-button>
        <el-button size="small" icon="el-icon-refresh" @click="search()">刷新</el-button>
      </el-row>
      <div class="mt-15">
        <el-table
          :data="list"
          style="width:100%"
          :border="true"
          @selection-change="selectionChange"
        >
          <el-table-column type="selection" width="60" />
          <el-table-column prop="id" label="ID" width="120" />
          <el-table-column prop="username" label="用户名" />
          <el-table-column prop="nickname" label="昵称" />
          <el-table-column prop="phoneNumber" label="手机" />
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" @click="edit(scope.row.id)">修改</el-button>
              <el-button size="mini" @click="del(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align:center; margin-top:10px">
        <el-pagination
          :total="total"
          :current-page="searchForm.pageNo"
          :page-size="searchForm.pageSize"
          :page-sizes="[2,10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="sizeChange()"
          @current-change="currentChange()"

        />
      </div>
    </el-card>

    <el-dialog
      :title="formTitle"
      :visible.sync="formShow"
      width="800px"
      :close-on-click-modal="false"
    >
      <el-form :model="form" :rules="rules" label-width="100px" ref="ruleForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username" size="small" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input v-model="form.password" type="password" size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="form.nickname" size="small" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机" prop="phoneNumber">
              <el-input v-model="form.phoneNumber" size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" />
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button size="small">取消</el-button>
        <el-button size="small" type="primary" @click="submit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<!-- js -->
<script>
import { getAdmin, listAdmin, addAdmin, delAdmin, editAdmin } from "@/api/admin";
export default {
  name: "Admin",
  data() {
    return {
      searchForm: {
        username: "",
        phoneNumber: "",
        pageNo: 1,
        pageSize: 10
      },
      list: [
        {
          id: 1,
          username: "admin",
          nickname: "锋行天下",
          phoneNumber: "13450233156"
        },
        {
          id: 2,
          username: "tom",
          nickname: "锋行天下",
          phoneNumber: "13450233156"
        }
      ],
      total: 0, //总页数
      ids: [], //用于批量删除
      formShow: false, //默认为隐藏
      form: {
        id: undefined,
        username: "",
        password: "",
        nickname: "",
        phoneNumber: "",
        remark: ""
      },
      formTitle: "",
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 6, max: 16, message: "长度在 5 到 16 个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 16, message: "长度在 6 到 16 个字符", trigger: "blur" }
        ]
      }
    };
  },
  created() { //生命周期方法，页面刚打开时调用
    this.search();
  },
  methods: {
    sizeChange(val){
        this.searchForm.pageSize=val;
        this.search();
    },
    currentChange(val){
        this.searchForm.pageNo=val;
        this.search();
    },
    search() {
      //查询
      console.log("--------------search------------", this.searchForm);
      listAdmin(this.searchForm)
        .then(res => {
          console.log(res);
          if (res.code == 20000) {
            this.list = res.data.list;
            this.total = res.data.total;
          }
        })
        .catch(err => {});
    },
    selectionChange(val) {
      //console.log(val);
      this.ids = val.map(o => {
        return o.id;
      }); //把选中的每一条记录的id取出来，组成一个新的数组，用于批量删除
      //console.log(this.ids);
    },
    del(id) {
      //删除
      let ids = id || this.ids;
      this.$confirm("确认删除？", "提示")
        .then(res => {
          console.log("111");
          //调接口删除
          delAdmin(ids).then(res => {
            if(res.code == 20000){
              this.$message("删除成功");
            }
          }).catch(err => {})
          //删除之后重新加载数据
        })
        .catch(err => {
          console.log("222");
        });
    },
    add() { //只是打开添加框，并未提交数据，在submit中提交数据
      //打开弹出框
      this.formShow = true;
      this.formTitle = "添加";
      this.form = {
        id: undefined,
        username: "",
        password: "",
        nickname: "",
        phoneNumber: "",
        remark: ""
      }
    },
    edit(id){ //打开弹出框，并且查询数据的数据
        console.log(getAdmin.id)
      getAdmin(id).then(res=>{
        if(res.code == 20000){
          this.form = res.data;
          this.formShow = true;
          this.formTitle = '修改';
          this.form.password = "111111";
        }
      }).catch(err=>{});
    },
    submit() { //提交
      console.log(this.$refs['ruleForm'],'9999')
      console.log(this.form.data)
      this.$refs['ruleForm'].validate(valid => {
        if (valid) {
          if(this.form.id && this.form.id > 0){ //id有值表示修改
            editAdmin(this.form).then(res=>{
            if(res.code === 20000){
              this.$message("修改成功");
              this.search();
              this.formShow = false;//关闭弹出框
            }
          }).catch(err=>{});
          }else{//添加
            addAdmin(this.form).then(res=>{
            if(res.code === 20000){
              this.$message("添加成功");
              this.reset(); //重置， 变回第1页
              this.formShow = false;//关闭弹出框
            }
          }).catch(err=>{});
          }

        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    reset(){ //重置查询,就是恢复查询条件
      this.searchForm = {
        username: "",
        phoneNumber: "",
        pageNo: 1,
        pageSize: 10
      };
      this.search();
    }
  }
};
</script>

<!-- css -->
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
