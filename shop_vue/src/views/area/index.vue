<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" round style="margin-left: 10px;" type="success" icon="el-icon-edit" @click="handleCreate()">
        增加区域
      </el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      element-loading-text="加载中"
      border
      fit
      style="width: 100%;"
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="rows">
          {{ rows.$index }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="区域ID">
        <template slot-scope="rows">
          {{ rows.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="区域名称">
        <template slot-scope="rows">
          {{ rows.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
        <template slot-scope="rows">
          <el-button type="info" round size="medium" @click="handleInfo(rows.row)">
            查看
          </el-button>
          <el-button type="primary" round size="medium" @click="handleUpdate(rows.row)">
            编辑
          </el-button>
          <el-popconfirm
            confirm-button-text="好的"
            confirm-button-type="danger"
            cancel-button-text="不用了"
            cancel-button-type="primary"
            icon="el-icon-delete"
            icon-color="red"
            title="确定删除吗？"
            @onConfirm="delPro(rows.row)"
          >
            <el-button slot="reference" type="danger" round size="medium">
              删除
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" center>
      <el-form ref="dataForm" status-icon :rules="rules" :model="temp" label-position="right" label-width="auto" style="width: 100%; margin-left:10px;" @keyup.enter.native="dialogStatus==='create'?createData():updateData()">
        <el-form-item label="名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="dialogFormVisible = false">
          关闭
        </el-button>
        <el-button round type="primary" @click="dialogStatus==='create'?createData():updateData()">
          提交
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogInfoVisible" center>
      <el-form disabled status-icon :model="InfoData" label-position="right" label-width="auto" style="width: 100%; margin-left:10px;">
        <el-form-item label="id">
          <span>{{ InfoData.id }}</span>
        </el-form-item>
        <el-form-item label="名称">
          <span>{{ InfoData.name }}</span>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogInfoVisible = false">确认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import request from '@/utils/request'
export default {
  components: {
    Pagination
  },
  data() {
    return {
      list: null,
      listLoading: true,
      total: 0,
      listQuery: {
        page: 1,
        limit: 10
      },
      temp: {
        id: '',
        name: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      dialogInfoVisible: false,
      InfoData: {
        id: '',
        name: ''
      },
      textMap: {
        update: '修改区域',
        create: '添加区域',
        info: '查看区域'
      },
      rules: {
        name: [{
          required: true,
          message: '区域名称不能为空',
          trigger: 'blur'
        },
        {
          min: 1,
          max: 50,
          message: '长度在 1 到 50 个字符',
          trigger: 'blur'
        }]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      request({
        url: '/area/getListByAllWithPage',
        method: 'POST',
        params: {
          page: this.listQuery.page,
          rows: this.listQuery.limit
        }
      }).then(response => {
        this.list = response.data
        this.total = response.count
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 0.2 * 1000)
      })
    },
    resetTemp() {
      this.temp = {
        id: '',
        name: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          request({
            url: '/area/add',
            method: 'POST',
            data: this.temp
          }).then(response => {
            this.dialogFormVisible = false
            this.$message({
              message: response.message,
              type: response.code === 20000 ? 'success' : 'error'
            })
            this.$notify({
              title: '信息',
              message: response.message,
              type: response.code === 20000 ? 'success' : 'error',
              duration: 1000
            })
            this.getList()
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          request({
            url: '/area/modify',
            method: 'POST',
            data: this.temp
          }).then(response => {
            this.dialogFormVisible = false
            this.$message({
              message: response.message,
              type: response.code === 20000 ? 'success' : 'error'
            })
            this.$notify({
              title: '信息',
              message: response.message,
              type: response.code === 20000 ? 'success' : 'error',
              duration: 1000
            })
            this.getList()
          })
        }
      })
    },
    onCancel() {
      this.$message({
        message: '返回到列表页面',
        type: 'warning'
      })
      this.dialogFormVisible = false
    },
    delPro(row) {
      this.temp = Object.assign({}, row) // copy obj
      request({
        url: '/area/delete',
        method: 'POST',
        data: this.temp
      }).then(response => {
        this.$message({
          message: response.message,
          type: response.code === 20000 ? 'success' : 'error'
        })
        this.$notify({
          title: '信息',
          message: response.message,
          type: response.code === 20000 ? 'success' : 'error',
          duration: 1000
        })
        this.getList()
      })
    },
    handleInfo(row) {
      request({
        url: '/area/getById',
        method: 'POST',
        params: {
          id: row.id
        }
      }).then(response => {
        this.InfoData = response.data
        this.picUrl = process.env.VUE_APP_BASE_API + 'product/showphoto/' + this.InfoData.id
        this.dialogStatus = 'info'
        this.dialogInfoVisible = true
      })
    }
  }
}
</script>
