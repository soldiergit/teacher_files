layui.use(['element', 'form', 'table', 'layer', 'laytpl', 'util'], function () {
    var $ = layui.$,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element, //Tab的切换功能，切换事件监听等，需要依赖element模块
        form = layui.form,
        laytpl = layui.laytpl,
        table = layui.table,
        util = layui.util;

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

    //基本信息列表
    var teacherId = window.sessionStorage.getItem("teacherId");
    $.ajax({
        type: "POST",
        url: '/teacher_files_war/biz/teacher_findById.action',//数据接口
        data: {teacherId: teacherId},
        success: function (data) {
            if (data.code == 0) {
                //myInfo 即 class="layui-form" 所在元素对应的 lay-filter="" 对应的值
                form.val("myInfo", {//表单回显
                    "teacherCode": data.data.teacherCode,
                    "teacherName": data.data.teacherName,
                    "deptName": data.data.dept.deptName,
                    "roleNames": data.data.roleNames,
                    "teacherSex": data.data.teacherSex == 1 ? '男' : '女',
                    "teacherBirth": util.toDateString(data.data.teacherBirth, "yyyy年MM月dd日"),
                    "entryTime": util.toDateString(data.data.entryTime, "yyyy年MM月dd日"),
                    "highEdu": data.data.highEdu,
                    "firstEdu": data.data.firstEdu,
                    "technicalPost": data.data.technicalPost,
                    "administPost": data.data.administPost,
                    "teacherResume": data.data.teacherResume,
                    "other": data.data.other,
                    "unitHide": data.data.unitIds,  //中间变量--防止用户重复刷新
                });
                $('#imgPreview').attr('src', data.data.teacherImg); //图片显示
                var arr = data.data.unitIds.split(',');  //教研室复选框选中
                if (arr != "" && arr.length != 0) {
                    for ( var i = 0; i <arr.length; i++){
                        $(".unitIds input[value="+arr[i]+"]").prop("checked",true);
                    }
                }
                //重新渲染checkbox
                form.render('checkbox');
                form.render();
            } else {
                layer.msg("未知错误，请联系管理员！" + data.msg);
            }
        },
        error: function () {
            layer.msg("可能是因为网络原因操作失败了，请重试，若多次重试不成功，请于网站管理员联系");
        }
    });

    //论文信息列表
    var paperInfoList = table.render({
        elem: '#paperInfoList',//数据表格id
        url: '/teacher_files_war/biz/paper_findByAuthor.action?teacherId=' + teacherId,//数据接口
        page: true,//开启分页
        height: "full-125",//容器高度
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "paperInfoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            // {type: "checkbox", fixed: "left", width: 50},
            {field: 'paperName', title: '论文名称', minWidth: 100, align: 'center'},
            {field: 'paperType', title: '论文类型', minWidth: 100, align: 'center', templet: function (d) {
                    if (d.paperType == '1') return '教改类';
                    if (d.paperType == '2') return '科研类';
                }},
            {field: 'teacherType', title: '作者类型', minWidth: 100, align: 'center', templet: function (d) {
                    if (d.teacherType == '1') return '第一作者或通讯作者';
                    if (d.teacherType == '2') return '独立作者';
                    if (d.teacherType == '3') return '其它';
                }},
            {field: 'signUnit', title: '署名单位', minWidth: 100, align: 'center', templet: function (d) {
                    if (d.signUnit == '1') return '第一署名单位';
                    if (d.signUnit == '2') return '第二署名单位';
                    if (d.signUnit == '3') return '其它';
                }},
            {field: 'periodicalName', title: '发布期刊名称', minWidth: 100, align: 'center'},
            {field: 'periodicalNumber', title: '期刊号', minWidth: 100, align: 'center'},
            {field: 'publishTime', title: '发布时间', minWidth: 100, align: 'center', templet: function (d) {
                    return util.toDateString(d.publishTime, "yyyy年MM月dd日");
                }},
            {field: 'paperGrade', title: '论文等级', minWidth: 100, align: 'center', templet: function (d) {
                    return d.paperGrade.title;
                }},
            {field: 'paperTitle', title: '论文标题', minWidth: 100, align: 'center'},
            {field: 'teacherName', title: '作者', minWidth: 100, align: 'center', templet: function (d) {
                    return d.teacher.teacherName;
                }},
        ]]
    });

    //赛事信息列表
    var matchInfoList = table.render({
        elem: '#matchInfoList',//数据表格id
        url: '/teacher_files_war/biz/match_findByTeacher.action?teacherId=' + teacherId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id : "matchInfoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {field: 'matchName', title: '赛事名称', minWidth:100, align:"center"},
            {field: 'matchLevel', title: '赛事级别', minWidth:200, align:'center', templet:function(d){
                    if (d.matchLevel=='1') return '国家级';
                    if (d.matchLevel=='2') return '省级';
                }},
            {field: 'matchType', title: '赛事类型', minWidth:100, align:'center', templet:function(d){
                    if (d.matchType=='1') return '学科竞赛';
                    if (d.matchType=='2') return '教学竞赛';
                    if (d.matchType=='3') return '创新创业';
                    if (d.matchType=='4') return '其它';
                }},
            {field: 'organizer', title: '主办单位', minWidth:100, align:'center'},
            {field: 'contractor', title: '承办单位', minWidth:100, align:'center'},
            {field: 'teacherName', title: '负责人', minWidth:100, align:'center', templet:function(d){
                    return d.teacher.teacherName;
                }},
            {field: 'deptName', title: '所在部门', minWidth:100, align:'center', templet:function(d){
                    return d.teacher.dept.deptName;
                }},
        ]]
    });

    //竞赛项目信息列表
    var raceInfoList = table.render({
        elem: '#raceInfoList',//数据表格id
        url: '/teacher_files_war/biz/competition_findByTeacher.action?teacherId='+teacherId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id : "raceInfoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {field: 'prizeImg', title: '获奖证书', minWidth: 100, align: 'center', templet: function (d) {
                    return '<img layer-src="'+d.prizeImg+'" src="'+d.prizeImg+'"/>';
                }},
            {field: 'itemName', title: '作品名称/比赛科目', minWidth:100, align:"center"},
            {field: 'matchName', title: '赛事名称', minWidth:100, align:"center", templet:function(d){
                    return d.match.matchName;
                }},
            {field: 'awardee', title: '获奖人', minWidth:100, align:"center"},
            {field: 'prizeTime', title: '获奖时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.prizeTime, "yyyy年")
                }},
            {field: 'prizeLevel', title: '获奖级别', minWidth:100, align:'center', templet:function(d){
                    return d.prizeLevel.title;
                }},
            {field: 'prizeGrade', title: '获奖等次', minWidth:100, align:'center', templet:function(d){
                    return d.prizeGrade.title;
                }},
            {field: 'teacherName', title: '指导老师', minWidth:100, align:'center', templet:function(d){
                    return d.teacher.teacherName;
                }},
        ]],done:function(res,curr,count){
            hoverOpenImg();//显示大图
            $('table tr').on('click',function(){
                $('table tr').css('background','');
                $(this).css('background','<%=PropKit.use("config.properties").get("table_color")%>');
            });
        }
    });

    //科研信息列表
    var teacherItemInfoList = table.render({
        elem: '#teacherItemInfoList',//数据表格id
        url: '/teacher_files_war/biz/teacherItem_findByPerson.action?itemPersonId='+teacherId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id : "teacherItemInfoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {field: 'itemName', title: '项目名称', minWidth:200, align:"center"},
            {field: 'personName', title: '主持人', minWidth:100, align:'center',templet:function(d){
                    return d.itemPerson.teacherName;
                }},
            {field: 'itemType', title: '项目类型', minWidth:100, align:'center',templet:function(d){
                    if (d.itemType=='1') return '教改类';
                    if (d.itemType=='2') return '科研类';
                }},
            {field: 'itemCategory', title: '项目类别', minWidth:200, align:'center',templet:function(d){
                    return d.itemCategory.title;
                }},
            {field: 'createTime', title: '立项时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.createTime, "yyyy年")
                }},
            {field: 'itemMoney', title: '项目经费', minWidth:100, align:'center', templet: function (d) {
                    return d.itemMoney+"/万元"
                }},
            {field: 'itemLevel', title: '项目级别', minWidth:100, align:'center',templet:function(d){
                    return d.itemLevel.title;
                }},
            {field: 'contractNumber', title: '合同编号', minWidth:100, align:'center'},
            {field: 'memberName', title: '成员', minWidth:100, align:'center'},
            {field: 'startTime', title: '起始时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.startTime, "yyyy年MM月dd日")
                }},
            {field: 'endTime', title: '结束时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.endTime, "yyyy年MM月dd日")
                }},
        ]]
    });

    //显示大图片
    function show_img(t) {
        var t = $(t).find("img");
        //页面层
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['100%', '100%'], //宽高
            shadeClose: true, //开启遮罩关闭
            end: function (index, layero) {
                return false;
            },
            content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
        });
    }

    //鼠标经过查看图片
    function hoverOpenImg(){
        var img_show = null; // tips提示
        $('td img').hover(function(){
            //alert($(this).attr('src'));
            var img = "<img class='img_msg' src='"+$(this).attr('src')+"' style='width:130px;' />";
            img_show = layer.tips(img, this,{
                tips:[2, 'rgba(41,41,41,.5)']
                ,area: ['160px']
            });
        },function(){
            layer.close(img_show);
        });
        $('td img').attr('style','max-width:70px');
    }
})