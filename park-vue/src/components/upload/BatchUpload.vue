<template>
    <el-card>
        <el-upload
                ref="batch-upload"
                multiple
                class="upload-demo"
        action="#"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :list-type="type"
        :auto-upload="false"
        :before-upload="beforeUpload"
        :on-change="changeFile"
        >
        <el-button slot="trigger" size="small" type="primary">{{title}}</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
<!--        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
        </el-upload>


    </el-card>
</template>


<script>
    import { batchUploadFile } from '../../api/upload';
    export default {
        name:  'Dashboard' ,
        data() {
            return {
                list:[]
            }
        },
        props:{
            value:[],
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
            fileList() {
                return this.value
            },
        },
        methods: {
            submitUpload() {
                const formData = new FormData();
                this.list.forEach(file => {
                    formData.append('fileList', file.raw)
                });
                batchUploadFile(formData).then(res =>{
                    if (res.code === 1){
                        this.$message.success('上传成功');
                        const fileNameList = [];
                        for (let i=0;i<res.data.length;i++){
                            fileNameList.push({url:res.data[i]})
                        }
                        this.$emit('input', fileNameList)
                    }

                })


            },
            handleRemove(file, fileList) {
                console.log(file, fileList)
            },
            handlePreview(file) {
                console.log(file)
            },
            beforeUpload(file) {
                console.log(file)
            },
            changeFile(file, fileList) {
                this.list = fileList
            }
        }
    }
</script>


<style  scoped>
    .dashboard .container {
            margin: 30px;
        }

    .dashboard .text {
        font-size: 30px;
        line-height: 46px;
    }
    /deep/.el-upload{
        height: 60px;
    }
</style>
