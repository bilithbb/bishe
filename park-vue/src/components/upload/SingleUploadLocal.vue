<template>
    <el-card>
        <el-upload
            action="#"
            :data="dataObj"
            list-type="text"
            accept=".png"
            :multiple="false" :show-file-list="showFileList"
            :file-list="fileList"
            :on-remove="handleRemove"
            :http-request="uploadFileInfo"
            :on-preview="handlePreview">
            <el-button size="small" type="primary">{{title}}</el-button>
            <!--      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB</div>-->
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
            <img width="50%" :src="fileList[0].url" alt="">
        </el-dialog>
    </el-card>
</template>
<script>


import { singleUploadFile } from '../../api/upload';

export default {
    name: 'singleUploadLocal',
    props: {
       value: String,
        title:{
           type:String,
           default:'上传图片'
        },
        type:{
           type:String,
            default: 'picture'
        }
    },
    computed: {
        imageUrl() {
            return this.value;
        },
        imageName() {
            console.log('value:'+this.value);
            if (this.value != null && this.value !== '') {
                return this.value.substring(this.value.lastIndexOf("/") + 1);
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

            },
            dialogVisible: false
        };
    },
    methods: {

        uploadFileInfo(e){
            console.log(e);
            const file = e.file;
            let formData = new FormData();
            formData.append("file",file);
            singleUploadFile(formData).then(res =>{
                if (res.code === 1){
                    //上传成功
                    // this.form.imageUrl = res.baseUrl + res.data;
                    this.showFileList = true;
                    this.fileList.pop();
                    this.fileList.push({name: file.name, url: res.data });
                    this.emitInput(this.fileList[0].url);
                }
            })
        },

        emitInput(val) {
            this.$emit('input', val)
        },
        handleRemove() {
            this.emitInput('');
        },
        handlePreview() {
            this.dialogVisible = true;
        },
        beforeUpload(file) {
            console.log(file);
            // const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isPNG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isPNG && isLt2M;

        },
        // handleUploadSuccess(res, file) {
        //     console.log("上传成功...")
        //     this.showFileList = true;
        //     this.fileList.pop();
        //     // this.fileList.push({name: file.name, url: this.dataObj.host + '/' + this.dataObj.key.replace("${filename}",file.name) });
        //     this.fileList.push({name: file.name, url: res.data });
        //     this.emitInput(this.fileList[0].url);
        //     console.log(this.fileList[0].url);
        // }
    },
}
</script>
<style scoped>
    /deep/.el-upload{
        height: 60px;
    }
</style>


