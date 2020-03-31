/*
版权所有 (c) 2019, 深圳市天双科技有限公司
保留所有权利.

V 1.0
作者: tianshuang

描述: JGlobalKeyIF

历史: 1. 2019/01/19, 重新整理
      2. 2019/04/28, 增加GetFloatData
	  3. 2019/05/31, 增加HS532相关接口和函数
*/

package tianshuang.globalkey;
import tianshuang.globalkey.JGlobalKeyIFEventListener;

public class JGlobalKeyIF
{
	static
	{
		System.loadLibrary("globalkeyif_jni");
	}

	//主要配置参数
	public static class KeyConfig
	{
		public boolean	m_bIfRecord;			//是否录像
		public String	m_strRecordPath;		//录像路径
		public int		m_iRecordPackTime;		//录像打包时间
		public int		m_iReversingTime;		//倒车延时时间
		public boolean	m_bIfSteerStartGlobal;	//转向时是否开启全景（仅低速）
		public boolean	m_bIfBootStartGlobal;	//车辆起步是否开启全景
		public boolean	m_bIfLDWS;				//是否开启车道偏离
		public int		m_iLDWSStartSpeed;		//车道偏离开启速度
		public boolean	m_bIfBSDS;				//是否开启盲区报警
		public int		m_iBSDSStartSpeed;		//盲区报警开启速度
		public boolean	m_bIfBSDSScreen;		//是否在屏幕上显示盲区报警
		public boolean	m_bIfBSDSSound;			//是否播放盲区报警提示音
		public int		m_iSpeedCtrlParam1;		//车速控制参数1（倒车过快提醒）
	}

	//次要配置参数
	public static class SecConfig
	{
		public boolean	m_bIfMODS;				//是否开启移动侦测
		public boolean	m_bIfAPA;				//是否开启自动泊车
		public int		m_iMODSSpeed;			//移动侦测速度参数
		public int		m_iAPASpeed;			//自动泊车速度参数
		public boolean	m_bIfDOW;				//是否开启开门预警
		public int		m_iDOWSpeed;			//开门预警速度参数
		public boolean	m_bFastAutoExit;		//高速是否自动退出全景
		public int		m_iAutExitSpeed;		//自动退出全景速度
	}

	//航盛UI（南山航盛）配置参数
	public static class HSUIConfig
	{
		public boolean	m_bIfDrivingStaticTrace;		//是否开启倒车静态轨迹线（驾车辅助模式显示轨迹线）
		public boolean	m_bIfDrivingDynamicTrace;		//是否开启倒车动态轨迹线（后视图显示倒车辅助线）
		public boolean	m_bIfDrivingMergerTrace;		//是否开启拼接区域轨迹线（全视图显示倒车辅助线）
		public boolean	m_bIfRadar;						//是否开启雷达显示（显示障碍物警告）
		public boolean	m_bIfBVM;						//是否开启BVM显示（启动盲区监测系统）
		public boolean	m_bIfBVMTrace;					//是否开启BVM轨迹（盲区检测视频显示辅助线）
		public int		m_iDefaultMode;					//前模式：0前视图、1前广角视图、2左前视图、3右前视图、4顶部视图全屏
		public int		m_iDefaultModeB;				//后模式：0左后视图、1右后视图、2顶部视图全屏
		public boolean	m_bIfReserve1;					//预留
		public boolean	m_bIfReserve2;					//预留
		public boolean	m_bIfReserve3;					//预留
		public boolean	m_bIfReserve4;					//预留
		public int		m_iReserve1;					//预留
		public int		m_iReserve2;					//预留
		public int		m_iReserve3;					//预留
		public int		m_iReserve4;					//预留
	}

	//HS591相关配置
	public static class HS591Config
	{
		public boolean	m_bIfLeftSteerStartGlobal;	//左转向时是否开启全景
		public boolean	m_bIfRightSteerStartGlobal;	//右转向时是否开启全景
		public boolean  m_bIfRadarStartGlobal;      //雷达监测到信号是否开启全景
		public boolean  m_bIfWheelSteeringStartGlobal; //方向盘转动是否开启全景
		
		public boolean	m_bIfDrivingStaticTrace;		//是否开启倒车静态轨迹线（驾车辅助模式显示轨迹线）
		public boolean	m_bIfDrivingDynamicTrace;		//是否开启倒车动态轨迹线（前视图显示倒车辅助线）
		
		public boolean	m_bIfAdvanceStaticTrace;		//是否开启前进静态轨迹线（驾车辅助模式显示轨迹线）
		public boolean	m_bIfAdvanceDynamicTrace;		//是否开启前进动态轨迹线（前视图显示倒车辅助线）
	}
	
	//HS532相关配置
	public static class HS532Config
	{
		public boolean	m_bIfSteerStartGlobal;	        //转向时是否开启全景
		public boolean	m_bIfRadarStartGlobal;          //雷达监测到信号是否开启全景
		public boolean	m_bIfWheelSteeringStartGlobal;  //方向盘转动是否开启全景
	 
		public boolean	m_bIfMODS;				        //是否开启移动侦测
		public int		m_iMODSSpeed;			 		//移动侦测速度参数
		public int 		m_iUIType ;							//UI风格
		public int 		m_iCaliLineType;					//工厂标定线别
	}
	
	//设置监听回调函数
	//listener：监听者		iParam：监听参数 1apk监听 2client监听 3any
	//返回0
	public native int SetListener(JGlobalKeyIFEventListener listener,int iParam);

	// 设置浮点数据
	// 参考C头文件TSH_GlobalKeyIF_SetFloatData
	public native void SetFloatData(int iType,float fValue);

	// 功能：获取浮点数据，获取通过SetFloatData设置的浮点数据
	// 返回浮点数据
	public native float GetFloatData(int iType);
	
	// 设置整数数据，注意数据的传输方向
	// 参考C头文件TSH_GlobalKeyIF_SetIntData
	public native void SetIntData(int iType,int iValue);

	// 获取数据或者执行命令
	// 参考C头文件TSH_GlobalKeyIF_ExecCmd
	public native int ExecCmd(int iCmd,String strValue);

	// 功能：设置全局配置
	// 返回0
	public native int SetConfig(KeyConfig config);

	// 功能：获取全局配置
	// 返回0
	public native int GetConfig(KeyConfig config);

	// 功能：设置次要配置
	// 返回0
	public native int SetSecConfig(SecConfig config);

	// 功能：获取次要配置
	// 返回0
	public native int GetSecConfig(SecConfig config);

	// 功能：设置HSUI配置
	// 返回0
	public native int SetHSUIConfig(HSUIConfig config);

	// 功能：获取HSUI配置
	// 返回0
	public native int GetHSUIConfig(HSUIConfig config);
	
	// 功能：设置HS591配置
	// 返回0
	public native int SetHS591Config(HS591Config config);
	
	// 功能：获取HS591配置
	// 返回0
	public native int GetHS591Config(HS591Config config);
	
	// 功能：设置HS532配置
	// 返回0
	public native int SetHS532Config(HS532Config config);
	
	// 功能：获取HS532配置
	// 返回0
	public native int GetHS532Config(HS532Config config);
	
}

