<template>
    <div>
        <el-button type="success" @click="openFaceCapture">执行车牌抓拍</el-button>
        <el-dialog
                title="拍照上传"
                :visible.sync="visible"
                :modal="false"
                @close="onCancel"
                width="95%">
            <div class="box">
                <video id="videoCamera" class="canvas" :width="videoWidth" :height="videoHeight" autoPlay></video>
                <canvas id="canvasCamera" class="canvas" :width="videoWidth" :height="videoHeight"></canvas>
            </div>
            <div slot="footer">
                <el-button
                        @click="drawImage"
                        icon="el-icon-camera"
                        size="small">
                    拍照
                </el-button>
                <el-button
                        v-if="os"
                        @click="getCompetence"
                        icon="el-icon-video-camera"
                        size="small">
                    打开摄像头
                </el-button>
                <el-button
                        v-else
                        @click="stopNavigator"
                        icon="el-icon-switch-button"
                        size="small">
                    关闭摄像头
                </el-button>
                <el-button
                        @click="resetCanvas"
                        icon="el-icon-refresh"
                        size="small">
                    重置
                </el-button>
                <el-button
                        @click="onCancel"
                        icon="el-icon-circle-close"
                        size="small">
                    完成
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>

    import utilMethod from '../../utils/utilMethod';

    export default {
        name: 'Face',
        data(){
            return{
                visible: false,//弹窗
                os: true,//控制摄像头开关
                videoWidth: 500,
                videoHeight: 400,
                // 是否显示弹出层
                open: false,
                thisCanCas:null,
                thisContext:null,
                thisVideo:null,
            }
        },
        methods:{

            openFaceCapture(){
                this.visible = true;
                this.getCompetence()
            },

            onCancel() {
                this.visible = false;
                /* this.resetCanvas();*/
                this.stopNavigator();
                this.imgSrc = "";
                this.clearCanvas('canvasCamera');
            },
            // 调用摄像头权限
            getCompetence() {
                //必须在model中render后才可获取到dom节点,直接获取无法获取到model中的dom节点
                this.$nextTick(() => {
                    const _this = this;
                    this.os = false;//切换成关闭摄像头
                    this.thisCanCas = document.getElementById('canvasCamera');
                    this.thisContext = this.thisCanCas.getContext('2d');
                    this.thisVideo = document.getElementById('videoCamera');
                    // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
                    if (navigator.mediaDevices === undefined) {
                        navigator.menavigatordiaDevices = {}
                    }
                    // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
                    // 使用getUserMedia，因为它会覆盖现有的属性。
                    // 这里，如果缺少getUserMedia属性，就添加它。
                    if (navigator.mediaDevices.getUserMedia === undefined) {
                        navigator.mediaDevices.getUserMedia = function (constraints) {
                            // 首先获取现存的getUserMedia(如果存在)
                            let getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia;
                            // 有些浏览器不支持，会返回错误信息
                            // 保持接口一致
                            if (!getUserMedia) {
                                return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
                            }
                            // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
                            return new Promise(function (resolve, reject) {
                                getUserMedia.call(navigator, constraints, resolve, reject)
                            })
                        }
                    }
                    const constraints = {
                        audio: false,
                        video: {width: _this.videoWidth, height: _this.videoHeight, transform: 'scaleX(-1)'}
                    };
                    navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
                        // 旧的浏览器可能没有srcObject
                        if ('srcObject' in _this.thisVideo) {
                            _this.thisVideo.srcObject = stream
                        } else {
                            // 避免在新的浏览器中使用它，因为它正在被弃用。
                            _this.thisVideo.src = window.URL.createObjectURL(stream)
                        }
                        _this.thisVideo.onloadedmetadata = function (e) {
                            _this.thisVideo.play()
                        }
                    }).catch(err => {
                        this.$notify({
                            title: '警告',
                            message: '没有开启摄像头权限或浏览器版本不兼容.',
                            type: 'warning'
                        });
                    });
                });
            },
            //绘制图片
            drawImage() {
                // 点击，canvas画图
                this.thisContext.drawImage(this.thisVideo, 0, 0, this.videoWidth, this.videoHeight);
                // 获取图片base64链接
                this.imgSrc = this.thisCanCas.toDataURL('image/png');
                utilMethod.$emit('faceResult', this.imgSrc);
                this.visible = false;
                /*const imgSrc=this.imgSrc;*/
            },


            //清空画布
            clearCanvas(id) {
                let c = document.getElementById(id);
                let cxt = c.getContext("2d");
                cxt.clearRect(0, 0, c.width, c.height);
            },
            //重置画布
            resetCanvas() {
                this.imgSrc = "";
                this.clearCanvas('canvasCamera');
            },
            //关闭摄像头
            stopNavigator() {
                if (this.thisVideo && this.thisVideo !== null) {
                    this.thisVideo.srcObject.getTracks()[0].stop();
                    this.os = true;//切换成打开摄像头
                }
            },


            clearAddForm(){
                this.visible = false;
                this.imgSrc = "";
                this.clearCanvas('canvasCamera');
            },
        }
    };
</script>

<style scoped>

</style>
