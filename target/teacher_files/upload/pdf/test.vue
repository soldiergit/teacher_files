<template>
  <el-dialog
    :title="'详情'"
    width="80%"
    v-loading="dataListLoading"
    @close="closeDialog"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-row>
      <table border="1" cellspacing="0" width="100%" class="table">
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents" align="center">
          <th colspan="10">
            项目基本信息
          </th>
        </tr>
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr align='center' style="height: 2.5rem">
          <th colspan="2">项目名称</th>
          <td colspan="8"><span style="font-size: 1.1rem; font-weight: bolder" v-text="matchInfo.matchName" align="center"></span></td>
        </tr>
        <tr align='center'>
          <th colspan="2">赛事名</th>
          <td colspan="8" v-for="item in eventLists"
              :key="item.eventName"
              v-if="item.eventId === matchInfo.eventId">
            <span v-text="item.eventName" align="center"></span>
          </td>
        </tr>
        <tr align='center'>
          <th colspan="2">团队名称</th>
          <td colspan="8"><span v-text="matchInfo.matchTeamName"></span>
          </td>
        </tr>
        <tr align='center'>
          <th colspan="2">参赛作品类型</th>
          <td colspan="8" v-for="item in matchTypeList"
              :key="item.value"
              v-if="item.value === matchInfo.matchType">
            <span v-text="item.label" align="center"></span>
          </td>
        </tr>
        <tr align='center'>
          <th colspan="2">参赛组别</th>
          <td colspan="8" v-for="item in matchGroupTypeList"
              :key="item.value"
              v-if="item.value === matchInfo.matchGroupType">
            <span v-text="item.label" align="center"></span>
          </td>
          <!--<td>-->
            <!--<span v-for="temp in matchGroupTypeList"-->
                  <!--v-if="temp.value === item.matchInfoEntity.matchGroupType"-->
                  <!--v-text="temp.label"/>-->
          <!--</td>-->
        </tr>
        <tr align='center'>
          <th colspan="2">项目简介</th>
          <td colspan="8">
            <span v-text="matchInfo.matchDescribe" align="center"></span>
          </td>
        </tr>
        <tr align='center'>
          <th colspan="2">项目所获投资情况</th>
          <td colspan="8">
            <span v-text="matchInfo.matchBrightSpot" align="center"></span>
          </td>
        </tr>
        <tr align='center'>
          <th colspan="2">项目所获专利情况</th>
          <td colspan="8">
            <span v-text="matchInfo.matchExpect" align="center" style="padding: 0 1rem"></span>
          </td>
        </tr>

        <!--项目负责人开始-->
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents"><th colspan="10">参赛项目负责人信息</th></tr>
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <template v-for="perInfo in perInfoList">
          <tr align='center'>
            <th>姓名</th>
            <td><span v-text="perInfo.sysUserEntity.name" align="center"></span></td>
            <th>性别</th>
            <template v-for="sex in sexList">
              <td v-if="perInfo.perSex === sex.value" v-text="sex.label"></td>
            </template>
            <th>企业职务</th>
            <td><span v-text="perInfo.perPost" align="center"></span></td>
            <th>联系电话</th>
            <td><span v-text="perInfo.sysUserEntity.mobile" align="center"></span></td>
            <th>QQ号码</th>
            <td><span v-text="perInfo.perQq" align="center"></span></td>
          </tr>

          <tr align='center'>
            <th>政治面貌</th>
            <td><span v-text="perInfo.perPoliticsType" align="center"></span></td>
            <th>学号</th>
            <td><span v-text="perInfo.perStuNo" align="center"></span></td>
            <th>所在二级学院</th>
            <td>
              <span v-for="item in instituteList"
                    v-if="item.instituteId === perInfo.sysUserEntity.instituteId"
                    v-text="item.instituteName"
                    align="center"></span>
            </td>
            <th>所在年级</th>
            <td>
              <span v-for="item in gradeList"
                    v-if="item.gradeId === perInfo.gradeId"
                    v-text="item.gradeName"
                    align="center">
              </span>
            </td>
            <th>所在班级</th>
            <td><span v-text="perInfo.perClassNo" align="center"></span></td>
          </tr>
          <tr align='center'>
            <th>所在宿舍</th>
            <td><span v-text="perInfo.perCormNo" align="center"></span></td>
            <th>个人电子邮箱</th>
            <td colspan="2"><span v-text="perInfo.sysUserEntity.email" align="center"></span></td>
            <th colspan="1">身份证号码</th>
            <td colspan="4"><span v-text="perInfo.perCardNo" align="center"></span></td>
          </tr>
          <tr align='center'>
            <th colspan="2">在校期间担任学生职务情况</th>
            <td colspan="8"><span v-text="perInfo.perSchoolPost" align="center"></span></td>
          </tr>
          <tr align='center'>
            <th colspan="2">在校期间所获等级证书及技能证书</th>
            <td colspan="8"><span v-text="perInfo.perSchoolHonor" align="center"></span></td>
          </tr>
          <tr align='center'>
            <th colspan="2">社会实践主要成绩简述</th>
            <td colspan="8"><span v-text="perInfo.perSocialPractice" align="center"></span></td>
          </tr>
        </template>
        <!--项目负责人结束-->

        <!--员工信息开始-->
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents"><th colspan="10">项目参与者信息</th></tr>
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th>姓名</th>
          <th>性别</th>
          <th>学号</th>
          <th>所在二级学院</th>
          <th>所在年级</th>
          <th>所在班级</th>
          <th>所学专业</th>
          <th>联系电话</th>
          <th>QQ号</th>
          <th>个人电子邮箱</th>
        </tr>
        <template>
          <tr v-for="item in staffList" align="center">
            <td v-text="item.staffName"></td>
            <td v-for="sex in sexList"
                :key="sex.value"
                v-if="item.staffSex=== sex.value"
                v-text="sex.label">
            </td>
            <td v-text="item.staffStuNo"></td>
            <td>
              <span v-for="institute in instituteList"
                    v-if="item.instituteId === institute.instituteId"
                    v-text="institute.instituteName"
                    align="center"></span>
            </td>
            <td>
              <span v-for="grade in gradeList"
                    v-if="item.gradeId === grade.gradeId"
                    v-text="grade.gradeName"
                    align="center">
              </span>
            </td>
            <td colspan="1" v-text="item.staffClassNo"></td>
            <td colspan="1" v-text="item.staffCormNo"></td>
            <td colspan="1" v-text="item.staffTel"></td>
            <td colspan="1" v-text="item.staffQq"></td>
            <td colspan="1" v-text="item.staffEmail"></td>
          </tr>
        </template>
        <!--员工信息结束-->

        <!--导师信息开始-->
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents" align="center">
          <th colspan="10">指导老师信息</th>
        </tr>
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th>姓名</th>
          <th>性别</th>
          <th colspan="2">所在单位/二级学院</th>
          <th>职务</th>
          <th>职称</th>
          <th colspan="2">邮箱</th>
          <th colspan="2">联系电话</th>
          <!--<th colspan="2">身份证号</th>-->
        </tr>
        <template v-for="item in userTeacherInfoEntities">
          <tr align="center">
            <td v-text="item.sysUserEntity.name"></td>
            <td v-for="sex in sexList"
                :key="sex.value"
                v-if="item.teacherSex === sex.value"
                v-text="sex.label">
            </td>
            <td colspan="2">
                <span v-for="institute in instituteList" v-if="item.sysUserEntity.instituteId === institute.instituteId" v-text="institute.instituteName"></span>
            </td>
            <td v-text="item.teacherPost"></td>
            <td colspan="1">
              <span v-for="teacherTitle in teacherTitleList" v-if="item.teacherTitle === teacherTitle.titleId" v-text="teacherTitle.titleName"></span>
            </td>
            <td colspan="2">{{item.sysUserEntity.email}}</td>
            <td colspan="2">{{item.sysUserEntity.mobile}}</td>
            <!--<td colspan="2" v-text="item.teacherCardNo"></td>-->
          </tr>
        </template>
        <!--导师信息结束-->

        <!--成果/奖项开始-->
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr align="center" class="contents">
          <th colspan="10">所获成果/奖项</th>
        </tr>
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr>
          <th colspan="2">获奖等级</th>
          <th colspan="2">奖项名称</th>
          <th colspan="1">奖项等级</th>
          <th colspan="2">所获奖金（万元）</th>
          <th colspan="2">获奖时间</th>
          <th colspan="1">证书</th>
          <!--<th colspan="1">操作</th>-->
        </tr>
        <template>
          <tr v-for="item in awardList" align="center">
            <td colspan="2"
                v-for="type in awardTypeList"
                :key="type.value"
                v-if="type.value === item.awardType"
                v-text="type.label">
            </td>
            <td colspan="2" v-text="item.awardName"></td>
            <td colspan="1"
                v-for="type in awardRankList"
                :key="type.value"
                v-if="type.value === item.awardRank"
                v-text="type.label">
            </td>
            <td colspan="2" v-text="item.awardMoney"></td>
            <td colspan="2" v-text="(item.awardTime || '').split(' ')[0]"></td>
            <!--<td colspan="1" v-text="item.awardFileName"></td>-->
            <td colspan="1"><button v-if="item.awardPath!==''" @click="awardDown(item)">下载</button></td>
          </tr>
        </template>
        <!--成果/奖项结束-->

        <!--附件开始-->
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr align="center" class="contents">
          <th colspan="10">附件</th>
        </tr>
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th colspan="7">附件名</th>
          <th colspan="3">操作</th>
        </tr>
        <!--补助类型：1投资，2管理服务，3房租和宽带，4水电费，5一次性创业，6吸纳困难群体，7社会保险，8创业担保贷款-->
        <template>
          <tr v-for="item in attachList"
              align="center">
            <td colspan="7" v-text="item.attachName"></td>
            <td colspan="3"><button @click="attachDown(item)">下载</button></td>
          </tr>
        </template>
        <tr align='center'>
          <td colspan="10" style="height: 1.2rem"></td>
        </tr>
        <!--附件结束-->

        <!--评分开始-->
        <template v-for="item in roleLists"
                  v-if="item === 5 || item === 1">
          <tr align='center'>
            <td colspan="10" style="height: 1.2rem"></td>
          </tr>
          <tr align="center" class="contents">
            <th colspan="10">评分详情</th>
          </tr>
          <tr align='center'>
            <td colspan="10" style="height: 1.2rem"></td>
          </tr>
          <tr align='center'>
            <td colspan="5">平均分</td>
            <td colspan="5" v-if="matchInfo.matchScoreAvg !== null" v-text="matchInfo.matchScoreAvg + '分'"></td>
          </tr>
          <tr align='center'>
            <td colspan="10" style="height: 1.2rem"></td>
          </tr>
          <tr align='center'>
            <th colspan="2">评委</th>
            <th colspan="3">流程</th>
            <th colspan="2">分数</th>
            <th colspan="3">建议</th>
          </tr>
          <template v-for="type in reviewTypeList">
            <template v-for="(item, index) in reviewList"
                      v-if="type.value === item.apply && type.value === 'project_match_apply_status'" >
              <tr align="center">
                <td colspan="2" v-text="'评委:' + (index + 1)"></td>
                <td colspan="3" v-text="type.label"></td>
                <td colspan="2" v-text="item.score"></td>
                <td colspan="3" v-text="item.opinion"></td>
              </tr>
            </template>
          </template>
          <tr align='center'>
            <td colspan="10" style="height: 1.2rem"></td>
          </tr>
        </template>
        <!--评分结束-->
      </table>
    </el-row>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">关闭</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataListLoading: false,
        matchInfo: {},
        instituteList: this.$store.state.user.institute,
        gradeList: this.$store.state.user.grade,
        userTeacherInfoEntities: [],
        moneyList: [],
        station: {},
        roleLists: this.$store.state.user.roleIdList,
        stationList: [],
        teacherSet: '',
        teacherList: [],
        awardList: [],
        staffList: [],
        reviewList: [],
        attachList: [],
        subList: [],
        perInfoList: [],
        reviewTypeList: [
          {value: 'project_audit_apply_status', label: '训练申请流程'},
          {value: 'project_base_apply_status', label: '中心申请流程'},
          {value: 'project_match_apply_status', label: '比赛申请流程'},
          {value: 'project_finish_apply_status', label: '结题申请流程'}
        ],
        eventLists: this.$store.state.eventLists,
        matchTypeList: [
          {value: 1, label: '论文、调研报告'}, {value: 2, label: '视频'},
          {value: 3, label: '设计作品'}, {value: 4, label: '创业计划书'},
          {value: 5, label: '创新创业实践项目'}
        ],
        matchGroupTypeList: [
          {value: 1, label: '创意组'}, {value: 2, label: '初创组'},
          {value: 3, label: '成长组'}, {value: 4, label: '就业创业组'},
          {value: 5, label: '"青年红色梦之旅"赛道'}
        ],
        sexList: [
          {value: 1, label: '男'}, {value: 2, label: '女'}
        ],
        teacherTitleList: this.$store.state.user.title,
        awardTypeList: [
          {value: 1, label: '国际级'}, {value: 2, label: '国家级'},
          {value: 3, label: '省级'}, {value: 4, label: '市厅级'},
          {value: 5, label: '县局级'}, {value: 6, label: '校级'}
        ],
        awardRankList: [
          {value: 1, label: '特等奖'}, {value: 2, label: '一等奖'},
          {value: 3, label: '二等奖'}, {value: 4, label: '三等奖'},
          {value: 5, label: '优秀奖'}, {value: 6, label: '金奖'},
          {value: 7, label: '银奖'}, {value: 8, label: '铜奖'},
          {value: 9, label: '第一名'}, {value: 10, label: '第二名'},
          {value: 11, label: '第三名'}, {value: 12, label: '第四名'},
          {value: 13, label: '第五名'}, {value: 14, label: '其他'}
        ],
        dataForm: {
          id: 0,
          teacherName: '',
          teacherSex: '',
          teacherUnit: '',
          teacherWorkStatus: '',
          teacherPhone: '',
          teacherJob: '',
          teacherInstinct: '',
          baseId: ''
        },
        staticList: [
          '在驻',
          '孵化成功出园',
          '孵化失败出园'
        ]
      }
    },
    methods: {
      init (id) {
        this.visible = true
        this.dataListLoading = true
        this.dataForm.id = id || 0
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/innovate/match/info/info`),
            method: 'get',
            params: this.$http.adornParams({
              'matchId': this.dataForm.id
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.matchInfo = data.matchInfo.matchInfoEntity
              if (data.matchInfo.matchTeacherEntities.length > 0) {
                this.teacherList = data.matchInfo.matchTeacherEntities
                this.userTeacherInfoEntities = data.userTeacherInfoEntities
              } else {
                this.teacherList = []
              }
              if (data.matchInfo.userPersonInfoEntities.length > 0) {
                this.perInfoList = data.matchInfo.userPersonInfoEntities
              } else {
                this.perInfoList = []
                this.perInfoList.sysUserEntity = []
              }
              if (data.matchInfo.matchAttachEntities.length > 0) {
                this.attachList = data.matchInfo.matchAttachEntities
              } else {
                this.attachList = []
              }
              if (data.matchInfo.matchStaffInfoEntities.length > 0) {
                this.staffList = data.matchInfo.matchStaffInfoEntities
              } else {
                this.staffList = []
              }
              if (data.matchInfo.matchAwardEntities.length > 0) {
                this.awardList = data.matchInfo.matchAwardEntities
              } else {
                this.awardList = []
              }
              if (data.matchInfo.matchReviewEntities.length > 0) {
                this.reviewList = data.matchInfo.matchReviewEntities
              } else {
                this.reviewList = []
              }
              this.dataListLoading = false
            }
          })
        } else {
          this.dataListLoading = false
        }
      },
      awardDown (award) {
        this.$httpFile({
          url: this.$httpFile.adornUrl(`/innovate/match/attach/download`),
          method: 'post',
          params: this.$httpFile.adornParams({
            'filePath': award.awardPath
            // 'apply': 'project_base_apply_status'
          })
        }).then(response => {
          if (!response) {
            return
          }
          let url = window.URL.createObjectURL(new Blob([response.data]))
          let link = document.createElement('a')
          link.style.display = 'none'
          link.href = url
          link.setAttribute('download', award.awardFileName)
          document.body.appendChild(link)
          link.click()
        }).catch(err => {
          console.log(err)
        })
      },
      attachDown (attach) {
        // let url = this.$http.adornUrl(`/innovate/match/attach/download?token=${this.$cookie.get('token')}&filePath=${attach.attachPath}`)
        this.$httpFile({
          url: this.$httpFile.adornUrl(`/innovate/match/attach/download`),
          method: 'post',
          params: this.$httpFile.adornParams({
            'filePath': attach.attachPath
            // 'apply': 'project_base_apply_status'
          })
        }).then(response => {
          if (!response) {
            return
          }
          let url = window.URL.createObjectURL(new Blob([response.data]))
          let link = document.createElement('a')
          link.style.display = 'none'
          link.href = url
          link.setAttribute('download', attach.attachName)
          document.body.appendChild(link)
          link.click()
          link.remove()
        }).catch(err => {
          console.log(err)
        })
      },

      closeDialog () {
        this.visible = false
        this.$emit('refreshDataList')
      }
    }
  }
</script>
<style>
  .contents{
    height: 60px;
    font-size: 18px;
  }
  .el-card__body {
    padding: 10px;
  }
  .el-step__title {
    font-size: 12px;
    line-height: 28px;
  }
  .table {
    border: 1px solid #ddd;
    /*为表格设置合并边框模型*/
    border-collapse: collapse;
    /*列宽由表格宽度和列宽度设定*/
    table-layout: fixed;
  }
  .table td {
    /*允许在单词内换行。*/
    word-break: break-word;
    /*设置宽度*/
    width: 80%;
  }
  .table th {
    /*允许在单词内换行。*/
    word-break: break-word;
    /*设置宽度*/
    width: 80%;
  }
</style>
