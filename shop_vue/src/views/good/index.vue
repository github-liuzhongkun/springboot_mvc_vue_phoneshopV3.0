<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="listQuery.category" placeholder="请选择品牌" clearable style="width: auto" class="filter-item">
        <el-option v-for="c in category" :key="c.id" :label="c.name" :value="c.id" />
      </el-select>
      <el-button round class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" round style="margin-left: 10px;" type="success" icon="el-icon-edit" @click="handleCreate()">
        增加
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
      <el-table-column align="center" label="商品ID">
        <template slot-scope="rows">
          {{ rows.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="商品名称">
        <template slot-scope="rows">
          {{ rows.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="实际价格">
        <template slot-scope="rows">
          {{ rows.row.price }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="商品数量">
        <template slot-scope="rows">
          {{ rows.row.qty }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="产地">
        <template slot-scope="rows">
          {{ rows.row.city }}
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
        <el-row>
          <el-col :span="8">
            <el-form-item label="名称" prop="name">
              <el-input v-model="temp.name" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="品牌" prop="category.id">
              <el-select v-model="temp.category.id" clearable placeholder="请选择品牌">
                <el-option v-for="c in category" :key="c.id" :label="c.name" :value="c.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价格" prop="price">
              <el-input v-model.number="temp.price" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="数量" prop="qty">
              <el-input v-model.number="temp.qty" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产地" prop="city">
              <el-input v-model="temp.city" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="商品图片">
          <el-image v-show="dialogStatus==='update'" :src="picUrl" lazy>
            <div slot="error" class="image-slot">
              无图片
            </div>
          </el-image>
          <input ref="photofile" type="file" @change="getFile">
        </el-form-item>
        <el-form-item label="销售区域">
          <el-checkbox-group v-model="temp.areas">
            <el-checkbox-button v-for="area in areas" :key="area.id" :value="area.id" :label="area.id">
              {{ area.name }}
            </el-checkbox-button>
          </el-checkbox-group>
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
      <el-form status-icon :model="temp" label-position="right" label-width="auto" style="width: 100%; margin-left:10px;">
        <el-row>
          <el-col :span="8">
            <el-form-item label="id">
              <span>{{ temp.id }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="品牌">
              <span>{{ temp.category.name }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="名称">
              <span>{{ temp.name }}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="价格">
              <span>{{ temp.price }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="数量">
              <span>{{ temp.qty }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产地">
              <span>{{ temp.city }}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="商品图片">
          <el-image :src="picUrl" lazy>
            <div slot="error" class="image-slot">
              无图片
            </div>
          </el-image>
        </el-form-item>
        <el-form-item label="销售区域">
          <el-checkbox-group v-model="temp.areas">
            <el-checkbox-button v-for="area in areas" :key="area.id" :value="area.id" :label="area.id">
              {{ area.name }}
            </el-checkbox-button>
          </el-checkbox-group>
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
    // const validatePrice = (rule, value, callback) => {
    //   if (value.length > 11) {
    //     callback(new Error('价格只能是1到11位之间'))
    //   } else {
    //     callback()
    //   }
    // }
    // const validateQty = (rule, value, callback) => {
    //   if (value.length > 11) {
    //     callback(new Error('数量只能是1到11位之间'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      list: null,
      listLoading: true,
      total: 0,
      listQuery: {
        page: 1,
        limit: 10,
        category: ''
      },
      category: [],
      areas: [],
      picUrl: '',
      temp: {
        id: '',
        name: '',
        price: '',
        qty: '',
        city: '',
        category: {
          id: ''
        },
        goodInfo: {
          id: ''
        },
        areas: []
      },
      dialogFormVisible: false,
      dialogStatus: '',
      dialogInfoVisible: false,
      textMap: {
        update: '修改商品',
        create: '添加商品',
        info: '查看商品'
      },
      rules: {
        name: [{
          required: true,
          message: '产品名称不能为空',
          trigger: 'blur'
        },
        {
          min: 1,
          max: 50,
          message: '长度在 1 到 50 个字符',
          trigger: 'blur'
        }],
        'category.id': [{
          required: true,
          message: '品牌不能为空',
          trigger: 'change'
        }],
        price: [{
          required: true,
          message: '价格不能为空',
          trigger: 'blur'
          // validator: validatePrice
        }
        ],
        qty: [{
          required: true,
          message: '数量不能为空',
          trigger: 'blur'
          // validator: validateQty
        }
        ],
        city: [{
          required: true,
          message: '产地不能为空',
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
    resetTemp() {
      this.temp = {
        id: '',
        name: '',
        price: '',
        qty: '',
        city: '',
        category: {
          id: ''
        },
        goodInfo: {
          id: ''
        },
        areas: []
      }
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    getList() {
      this.listLoading = true
      request({
        url: '/category/getListByAll',
        method: 'POST'
      }).then(response => {
        this.category = response.data
      })
      request({
        url: '/good/getListByAllWithPage',
        method: 'POST',
        params: {
          page: this.listQuery.page,
          rows: this.listQuery.limit,
          category: this.listQuery.category
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
    getFile: function(event) {
      this.temp.photo = event.target.files[0]
    },
    handleCreate() {
      this.resetTemp()
      request({
        url: '/area/getListByAll',
        method: 'POST'
      }).then(response => {
        this.areas = response.data
      })
      request({
        url: '/category/getListByAll',
        method: 'POST'
      }).then(response => {
        this.category = response.data
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
          this.$refs.photofile.value = ''
        })
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          var formdata = new FormData()// 创建form对象
          formdata.append('name', this.temp.name)
          formdata.append('price', this.temp.price)
          formdata.append('qty', this.temp.qty)
          formdata.append('city', this.temp.city)
          formdata.append('category.id', this.temp.category.id)
          formdata.append('goodInfo.id', 0)
          formdata.append('areaNos', this.temp.areas)
          formdata.append('photo', this.temp.photo)
          request({
            url: '/good/add',
            method: 'POST',
            data: formdata
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
            this.$refs.photofile.value = ''
            this.getList()
          })
        }
      })
    },
    handleUpdate(row) {
      this.resetTemp()
      request({
        url: '/category/getListByAll',
        method: 'POST'
      }).then(response => {
        this.category = response.data
      })
      this.resetTemp()
      request({
        url: '/area/getListByAll',
        method: 'POST'
      }).then(response => {
        this.areas = response.data
      })
      request({
        url: '/good/getById',
        method: 'POST',
        params: {
          id: row.id
        }
      }).then(response => {
        this.temp = response.data
        for (var i = 0; i < this.temp.areas.length; i++) {
          this.temp.areas[i] = this.temp.areas[i].id
        }
        this.picUrl = process.env.VUE_APP_BASE_API + 'good/showphoto/' + this.temp.id + '/' + new Date()
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
          this.$refs.photofile.value = ''
        })
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          var formdata = new FormData()// 创建form对象
          formdata.append('id', this.temp.id)
          formdata.append('name', this.temp.name)
          formdata.append('price', this.temp.price)
          formdata.append('qty', this.temp.qty)
          formdata.append('city', this.temp.city)
          formdata.append('category.id', this.temp.category.id)
          formdata.append('goodInfo.id', this.temp.id)
          formdata.append('areaNos', this.temp.areas)
          formdata.append('photo', this.temp.photo)
          request({
            url: '/good/modify',
            method: 'POST',
            data: formdata
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
      request({
        url: '/good/delete',
        method: 'POST',
        data: row
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
        url: '/area/getListByAll',
        method: 'POST'
      }).then(response => {
        this.areas = response.data
      })
      request({
        url: '/good/getById',
        method: 'POST',
        params: {
          id: row.id
        }
      }).then(response => {
        this.temp = response.data
        for (var i = 0; i < this.temp.areas.length; i++) {
          this.temp.areas[i] = this.temp.areas[i].id
        }
        this.picUrl = process.env.VUE_APP_BASE_API + 'good/showphoto/' + this.temp.id + '/' + new Date()
        this.dialogStatus = 'info'
        this.dialogInfoVisible = true
      })
    }
  }
}
</script>
