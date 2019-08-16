layui.use([ 'form', 'layer', 'upload', 'laydate'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload,
        laydate = layui.laydate;

    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
    //渲染下拉框
    $.post("/teacher_files_war/biz/match_findAll.action", function (data) {//--赛事
        $.each(data.data, function (index, item) {
            $('#matchBunk').append(new Option(item.matchName, item.matchId));
        });
        if (updateFlag === '1') $('#matchBunk').val($('.matchHide').val());//使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });
    var deptId = window.sessionStorage.getItem("deptId");
    $.post("/teacher_files_war/biz/teacher_findAllByDept.action?deptId="+deptId, function (data) {//--指导老师
        $.each(data.data, function (index, item) {
            $('#teacherBunk').append(new Option(item.teacherName+"_"+item.dept.deptName, item.teacherId));
        });
        //如果是新增，指导老师老师默认选中自己
        if (updateFlag === '0') $('#teacherBunk').val(window.sessionStorage.getItem("teacherId"));
        else $('#teacherBunk').val($('.teacherHide').val());//使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });
    $.post("/teacher_files_war/biz/competitionPrizeLevel_findAll.action", function (data) {//--竞赛获奖级别
        $.each(data.data, function (index, item) {
            $('#prizeLevel').append(new Option(item.title, item.id));
        });
        if (updateFlag === '1') $('#prizeLevel').val($('.prizeLevelHide').val());
        //重新渲染select
        form.render('select');
    });
    $.post("/teacher_files_war/biz/competitionPrizeGrade_findAll.action", function (data) {//--竞赛获奖等次
        $.each(data.data, function (index, item) {
            $('#prizeGrade').append(new Option(item.title, item.id));
        });
        if (updateFlag === '1') $('#prizeGrade').val($('.prizeGradeHide').val());
        //重新渲染select
        form.render('select');
    });

    //添加验证规则
    form.verify({
        userFile: function (value) {if (value == null || value=="") return "请上传获奖证书！";}
    });

    //执行一个laydate实例
    laydate.render({elem: '#prizeTime', type: 'year', done: function (value, date, endDate) {}});

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher_files_war/biz/competition_save.action" : "/teacher_files_war/biz/competition_update.action", {

            itemId: updateFlag === '0' ? null : $(".Id").val(),//id
            itemName: $(".itemName").val(),
            itemPrizeTime: $(".prizeTime").val(),
            prizeImg: $(".prizeImg").val(),
            awardee: $(".awardee").val(),//获奖人
            prizeLevelId: data.field.prizeLevel,
            prizeGradeId: data.field.prizeGrade,
            matchId: data.field.matchId,
            teacherId: data.field.teacherId,
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

    //上传获奖证书图片
    var uploadInst = upload.render({
        elem: '#imgUpload',
        url: '/teacher_files_war/upload_uploadPrizeImg.action',
        acceptMime: 'image/*',  //只显示图片文件
        auto: false,
        bindAction: '#startUpload',
        //文件提交上传前的回调
        before: function(obj){
            layer.load(); //上传loading
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                // console.log(file);//里面的名称是选中时文件的名称
                $('#imgPreview').attr('src', result); //图片链接（base64）
            });
        },
        //执行上传请求后的回调。
        done: function(res){
            if (res.code===0){
                layer.msg('上传成功!');
                $('.prizeImg').val(res.src);
            }else {
                layer.msg('上传失败!');
            }
            layer.closeAll('loading'); //关闭loading
        },
        //执行上传请求出现异常的回调
        error: function(){
            //演示失败状态，并实现重传
            var reUpload = $('#reUpload');
            reUpload.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            reUpload.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    //拖拽上传pdf
    upload.render({
        elem: '#fileUpload',
        url: '/teacher_files_war/upload_uploadFile.action',
        accept: 'file',//指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
        exts: 'pdf',
        //文件提交上传前的回调
        before: function(obj){
            layer.load(); //上传loading
        },
        //执行上传请求后的回调
        done: function (res) {
            if (res.code === 0) {
                layer.msg('上传成功!');
                $('#filePath').val(res.src);
                console.log($('#filePath').val())
            } else {
                layer.msg('上传失败!');
            }
            layer.closeAll('loading'); //关闭loading
        }
    });

    //当用户选中赛事时，内容根据竞赛类型改变  -- competitionType:lay-filter绑定的名称
    form.on("select(matchType)", function (data) {
        if (data.value == 1) {  //如果是大设赛
            $('#workName').html("作品名称");
            $('#instructor').show();    //指导老师显示
            //如果是新增，指导老师老师默认选中自己
            if (updateFlag === '0') $('#teacherBunk').val(window.sessionStorage.getItem("teacherId"));
        } else if (data.value == 6) {   //如果是蓝桥杯
            $('#workName').html("比赛科目");
            $('#instructor').hide();    //指导老师隐藏
        } else {    //教学竞赛
            $('#workName').html("比赛科目");
            $('#instructor').hide();    //指导老师隐藏
        }
    });
});