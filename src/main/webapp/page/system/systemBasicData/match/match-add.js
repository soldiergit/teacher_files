layui.use([ 'form', 'layer', 'upload', 'laydate'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload,
        laydate = layui.laydate;

    //新增,更新
    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新

    //渲染下拉框
    var deptId = window.sessionStorage.getItem("deptId");
    $.post("/teacher_files_war/biz/teacher_findAllByDept.action?deptId="+deptId, function (data) {
        $.each(data.data, function (index, item) {
            $('#teacherBunk').append(new Option(item.teacherName+"_"+item.dept.deptName, item.teacherId));
        });
        if (updateFlag === '0') {//添加，选中自己
            $('#teacherBunk').val(window.sessionStorage.getItem("teacherId"));//.prop("disabled", true)
        } else {//更新，选中作者
            $('#teacherBunk').val($('.teacherHide').val());//使用中间变量让其默认选中
        }

        //重新渲染select
        form.render('select');
    });

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher_files_war/biz/match_save.action" : "/teacher_files_war/biz/match_update.action", {

            matchId: updateFlag === '0' ? null : $(".Id").val(),//id
            matchName: $(".matchName").val(),
            matchLevel: data.field.matchLevel,  //赛事级别
            matchType: data.field.matchType,  //赛事类型
            organizer: $(".organizer").val(),
            contractor: $(".contractor").val(),

            manyFilePath: $(".manyFilePath").val(),//文件路径
            manyFileName: $(".manyFileName").val(),//文件原名称
            manyFileType: $(".manyFileType").val(),//文件类型
            teacherId: data.field.teacherId,//通过name属性获取--赛事负责人
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加成功！" : "修改成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            } else {
                layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });

    var fileUrls = "";  //用于保存所有文件返回的地址
    var fileNames = "";  //用于保存所有文件返回的原文件名
    var fileTypes = "";  //用于保存所有文件返回的文件类型(后缀)
    //多文件列表示例
    var demoListView = $('#demoList'),
        uploadListIns = upload.render({
            elem: '#testList',
            url: '/teacher_files_war/upload_uploadMatchFile.action',
            accept: 'file', //指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
            size: 10240,    //设置单个文件最大可允许上传的大小，单位 KB
            number:10,    //设置单最大上传的数量
            multiple: true, //是否允许多文件上传
            auto: false,    //是否选完文件后自动上传。如果设定 false，那么需要设置 bindAction 参数来指向一个其它按钮提交上传
            bindAction: '#testListAction',
            choose: function(obj){  //选择文件后回调
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    var tr = $(['<tr id="upload-'+ index +'">',
                        '<td>'+ file.name +'</td>',
                        '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
                        '<td>等待上传</td>',
                        '<td>',
                        '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>',
                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                        '</td>',
                        '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            ,done: function(res, index, upload){    //执行上传请求后回调
                if(res.code == 0){ //上传成功
                    //保存这些文件的路径、原文件名、文件类型（后缀）
                    fileUrls = fileUrls+""+res.src+",";
                    fileNames = fileNames+""+res.fileName+",";
                    fileTypes = fileTypes+""+res.fileType+",";
                    $('#manyFilePath').val(fileUrls);
                    $('#manyFileName').val(fileNames);
                    $('#manyFileType').val(fileTypes);

                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            }
            ,error: function(index, upload){    //执行上传请求出现异常回调
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });

    //添加验证规则
    form.verify({
        manyFile: function (value) {
            if (value==null ||value=="" ||value==undefined) {
                return "请上传附件！";
            }
        }
    })
});