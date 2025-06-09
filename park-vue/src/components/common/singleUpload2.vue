<template> 
  <div>
    <el-upload
            action="http://gulimall-psw.oss-cn-hangzhou.aliyuncs.com"
            :data="dataObj"
            class="avatar-uploader"
            :show-file-list="false"
            :before-upload="beforeUpload"
            :on-remove="handleRemove"
            :on-success="handleUploadSuccess"
            :on-preview="handlePreview">
      <img v-if="fileList[0].url" :src="fileList[0].url" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="50%" :src="fileList[0].url" alt="">
    </el-dialog>
  </div>
</template>
<script>


  import { getUUID, policy } from '../../api/policy';

  export default {
    name: 'singleUpload',
    props: {
      value: String,
      type:{
        type:String,
        default:'picture'
      }
    },
    computed: {
      imageUrl() {
        return this.value;
      },
      imageName() {
        if (this.value != null && this.value !== '') {
          return this.value.substr(this.value.lastIndexOf("/") + 1);
        } else {
          return null;
        }
      },
      fileList() {
        return [{
          name: this.imageName,
          url: this.imageUrl
        }]
      },
      showFileList: {
        get: function () {
          return this.value !== null && this.value !== ''&& this.value!==undefined;
        },
        set: function (newValue) {
        }
      }
    },
    data() {
      return {
        dataObj: {
          policy: '',
          signature: '',
          key: '',
          ossaccessKeyId: '',
          dir: '',
          host: '',
          // callback:'',
        },
        dialogVisible: false
      };
    },
    methods: {
      emitInput(val) {
        this.$emit('input', val)
      },
      handleRemove(file, fileList) {
        this.emitInput('');
      },
      handlePreview(file) {
        this.dialogVisible = true;
      },
      beforeUpload(file) {
        let _self = this;
        return new Promise((resolve, reject) => {
          policy().then(response => {
            console.log(response)
            _self.dataObj.policy = response.policy;
            _self.dataObj.signature = response.signature;
            _self.dataObj.ossaccessKeyId = response.accessid;
            _self.dataObj.key = response.dir + '/'+getUUID()+'_${filename}';
            _self.dataObj.dir = response.dir;
            _self.dataObj.host = response.host;
            resolve(true)
          }).catch(err => {
            reject(false)
          })
        })
      },
      handleUploadSuccess(res, file) {
        console.log("上传成功...")
        this.showFileList = true;
        this.fileList.pop();
        this.fileList.push({name: file.name, url: this.dataObj.host + '/' + this.dataObj.key.replace("${filename}",file.name) });
        this.emitInput(this.fileList[0].url);
      }
    }
  }
</script>
<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    width: 100%;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  /deep/.avatar-uploader .el-upload--text{
    width: 100%;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100%;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 100%;
    height: 178px;
    display: block;
  }

  /deep/ .el-input--small{
    width: 400px;
  }
  /deep/ .el-form-item--mini.el-form-item, .el-form-item--small.el-form-item{
    margin-bottom: 38px;
  }
  /deep/ .avatar-uploader .el-upload--text{
    height: 100%!important;
  }
</style>


