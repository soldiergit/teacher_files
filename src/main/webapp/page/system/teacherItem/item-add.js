layui.use(['form','layer','upload','laydate','transfer','util'],function(){
    var $ = layui.$,
        transfer = layui.transfer, //穿梭框
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload,
        laydate = layui.laydate,
        util = layui.util;

    //新增,更新
    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新

    var deptId = window.sessionStorage.getItem("deptId");
    $.post("/teacher_files_war/biz/teacher_findAllByDept.action?deptId="+deptId, function (data) {
        $.each(data.data, function (index, item) {
            //下拉框--负责人
            $('#itemPerson').append(new Option(item.teacherName+"_"+item.dept.deptName, item.teacherId));
            //下拉框--搜索教师
            $('#searchTeacher').append(new Option(item.teacherName+"_"+item.dept.deptName, item.teacherId));
            //复选框--已选成员
            $('#itemMember').append('<input type="checkbox" name="itemMembers" value="'+item.teacherId+'" lay-skin="primary" title="'+item.teacherName+'"/>');
        });
        //更新的时候使用中间变量让其默认选中
        if (updateFlag==='1') {
            $('#itemPerson').val($('.personHide').val()).prop("disabled",true);//使用中间变量让其默认选中 【项目负责人】
            //多选框默认选中--js传递过来的数组会变成字符串，所有要转换--记得数组非空判断   【项目成员】
            var arr = $('.memberHide').val().split(',');
            if (arr != "" && arr.length != 0) {
                for ( var i = 0; i <arr.length; i++){
                    $(".itemMember input[value="+arr[i]+"]").prop("checked","checked").prop("disabled",true);
                }
            }
        } else {
            //否则根据当前用户选中
            var thisTeacher = window.sessionStorage.getItem('teacherId');
            $('#itemPerson').val(thisTeacher).prop("disabled",true);
            $(".itemMember input[value="+thisTeacher+"]").prop("checked","checked").prop("disabled",true);//当前用户就是成员--因为其被作为负责人选中
        }
        $('#searchTeacher').val('');
        //重新渲染select
        form.render('select');
        //重新渲染checkbox
        form.render('checkbox');
    });
    $.post("/teacher_files_war/biz/teacherItemCategory_findAll.action", function (data) {//--教师项目类别
        $.each(data.data, function (index, item) {
            $('#itemCategory').append(new Option(item.title, item.id));
        });
        if (updateFlag === '1') $('#itemCategory').val($('.itemCategoryHide').val());
        //重新渲染select
        form.render('select');
    });
    $.post("/teacher_files_war/biz/teacherItemLevel_findAll.action", function (data) {//--教师项目级别
        $.each(data.data, function (index, item) {
            $('#itemLevel').append(new Option(item.title, item.id));
        });
        if (updateFlag === '1') $('#itemLevel').val($('.itemLevelHide').val());
        //重新渲染select
        form.render('select');
    });

    //执行一个laydate实例
    laydate.render({elem: '#createTime' , type: 'year', done: function(value, date, endDate){//控件选择完毕后的回调
            //当用户选中立项时，起始时间变为当年1.1.结束时间变为12.31
            $(".startTime").val(value+"-01-01");
            $(".endTime").val(value+"-12-31");
        }});
    laydate.render({elem: '#startTime' , type: 'date', done: function(value, date, endDate){}});
    laydate.render({elem: '#endTime' , type: 'date', done: function(value, date, endDate){}});

    form.on("submit(addUser)",function(data){
        var ids = [];
        var names = [];
        $('input[name="itemMembers"]:checked').each(function(){
            ids.push($(this).val());//将选中的值添加到数组chk_value中
            names.push(this.title);//将选中的值添加到数组chk_value中
        });
        //弹出loading
        var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post(updateFlag==='0'?"/teacher_files_war/biz/teacherItem_save.action":"/teacher_files_war/biz/teacherItem_update.action",{

            itemId : updateFlag==='0'?null:$(".Id").val(),//id
            itemName : $(".itemName").val(),  //登录名
            itemType : $(".itemType").val(),
            contractNumber : $(".contractNumber").val(),
            itemMoney : $(".itemMoney").val(),
            itemCreateTime : $(".createTime").val(),
            itemStartTime : $(".startTime").val(),
            itemEndTime : $(".endTime").val(),
            itemPersonId : $(".itemPerson").val(),//主持人
            itemMember : ids.join(','),//成员+主持人--》id
            memberName : names.join(','),//成员+主持人--》姓名
            itemCategoryId : $(".itemCategory").val(),
            itemLevelId : $(".itemLevel").val(),
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

    //当用户切换项目类型时，项目类别和项目级别都改变  -- itemType:lay-filter绑定的名称
    // form.on("select(itemType)", function (data) {
    //     if (data.value == 1) {//教改类
    //         $(".itemCategory option[value="+1+"]").css("display", "none");
    //     }
    // });

    //当用户选中负责人时，成员多选框中自动选中负责人  -- itemPerson:lay-filter绑定的名称
    form.on("select(itemPerson)", function (data) {
        $(".itemMember input[value="+data.value+"]").prop("checked","checked");
        //重新渲染checkbox
        form.render('checkbox');
    });

    //当用户选中成员时，成员多选框中自动该成员  -- searchTeacher:lay-filter绑定的名称
    form.on("select(searchTeacher)", function (data) {
        $(".itemMember input[value="+data.value+"]").prop("checked","checked");
        //重新渲染checkbox
        form.render('checkbox');
    })


});