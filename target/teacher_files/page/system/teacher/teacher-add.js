layui.use(['form','layer','upload','laydate'],function(){
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate;

    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新

    //执行一个laydate实例
    laydate.render({elem: '#teacherBirth' , type: 'date',  done: function(value, date, endDate){}});
    laydate.render({elem: '#entryTime' , type: 'date',  done: function(value, date, endDate){}});

    //渲染复选框数据
    $.post("/teacher_files_war/biz/dept_findAll.action", function (data) {
        $.each(data.data, function (index, item) {
            $('#deptId').append(new Option(item.deptName, item.deptId));
        });
        if (updateFlag==='1') $('#deptId').val($('.deptHide').val()).prop("disabled", true);//如果是更新，使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });
    //渲染复选框数据
    var deptId = window.sessionStorage.getItem("deptId");
    $.post("/teacher_files_war/biz/dept_findSubordinate.action?deptId="+deptId, function (data) {//--所属教研室
        $.each(data.data, function (index, item) {
            $('#unitIds').append('<input type="checkbox" name="unitIds" value="'+item.deptId+'" lay-skin="primary" title="'+item.deptName+'"/>');
        });
        var arr = $('.unitHide').val().split(',');  //教研室复选框选中
        if (arr != "" && arr.length != 0) {
            for ( var i = 0; i <arr.length; i++){
                $(".unitIds input[value="+arr[i]+"]").prop("checked",true);
            }
        }
        //重新渲染checkbox
        form.render('checkbox');
    });
    $.post("/teacher_files_war/role_findByOtherRole.action", function (data) {
        $.each(data.data, function (index, item) {
            // $('#roleSet').append('<input type="checkbox" name="roleIds" value="'+item.roleId+'" lay-skin="primary" title="'+item.roleName+'"/>');
            $('#roleSet').append('<input type="radio" name="roleIds" value="'+item.roleId+'" title="'+item.roleName+'"/>');
        });
        if (updateFlag==='1') {//更新的时候使用中间变量让其默认选中
            $('.roleSet input').prop("disabled",true);  //角色-无法修改
            //多选框默认选中--js传递过来的数组会变成字符串，所有要转换--记得数组非空判断
            var arr = $('.roleHide').val().split(',');
            if (arr != "" && arr.length != 0) {
                for ( var i = 0; i <arr.length; i++) {
                    $(".roleSet input[value=" + arr[i] + "]").prop("checked", "checked");
                }
            }
        } else $(".roleSet input[value="+4+"]").prop("checked","checked");
        //重新渲染checkbox
        // form.render('checkbox');
        form.render('radio');
    });

    //添加验证规则
    // form.verify({
    //     userFile: function (value) {
    //         if (value == null || value=="") {
    //             return "请上传个人照片！";
    //         }
    //     }
    // });

    form.on("submit(addUser)",function(data){
        //角色
        var ids = [];
        var names = [];
        $('input[name="roleIds"]:checked').each(function(){
            ids.push($(this).val());//将选中的值添加到数组chk_value中
            names.push(this.title);//将选中的值添加到数组chk_value中
        });

        //所属教研室
        var unitId = [];
        $('input[name="unitIds"]:checked').each(function(){
            unitId.push($(this).val());//将选中的值添加到数组chk_value中
        });

        //弹出loading
        var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post(updateFlag==='0'?"/teacher_files_war/biz/teacher_save.action":"/teacher_files_war/biz/teacher_update.action",{

            teacherId : updateFlag==='0'?null:$(".Id").val(),//id
            teacherCode : $(".teacherCode").val(),  //登录名
            teacherName : $(".teacherName").val(),
            deptId : $(".deptId").val(),//部门
            unitIds : unitId.join(','),//部门
            roleIds : ids.join(','),//角色
            roleNames : names.join(','),//角色名称
            teacherSex : data.field.teacherSex,  //性别
            theTeacherBirth : $(".teacherBirth").val(),
            theTeacherEntryTime : $(".entryTime").val(),
            teacherImg : $(".teacherImg").val(),  //头像
            highEdu : $(".highEdu").val(),
            firstEdu : $(".firstEdu").val(),
            technicalPost : $(".technicalPost").val(),
            administPost : $(".administPost").val(),
            teacherResume : $(".teacherResume").val(),
            other : $(".other").val(),
        },function(res){
            if (res.code === 0){
                top.layer.close(index);
                top.layer.msg(updateFlag==='0'?"添加成功！":"修改成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }else {
                layer.close(index);
                top.layer.msg("操作失败！");
            }
        })
        return false;
    });

    //上传头像
    var uploadInst = upload.render({
        elem: '#imgUpload',
        url: '/teacher_files_war/upload_uploadTeacherImg.action',
        acceptMime: 'image/*',  //只显示图片文件
        size: 100,
        auto: false,
        bindAction: '#startUpload',
        //文件提交上传前的回调
        before: function(obj){
            layer.load(); //上传loading
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                console.log(file);//里面的名称是选中时文件的名称
                $('#imgPreview').attr('src', result); //图片链接（base64）
            });
        },
        //执行上传请求后的回调。
        done: function(res){
            if (res.code===0){
                layer.msg('上传成功!');
                $('#imgPath').val(res.src);
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
});