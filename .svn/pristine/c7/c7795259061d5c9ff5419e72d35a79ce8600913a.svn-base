package com.ntels.ccbs.system.service.menu.menuMng.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.common.exception.ServiceException;
import com.ntels.ccbs.system.domain.auth.authMng.AuthMngVO;
import com.ntels.ccbs.system.domain.menu.menuMng.MenuMngVO;
import com.ntels.ccbs.system.mapper.menu.menuMng.MenuMngMapper;
import com.ntels.ccbs.system.service.menu.menuMng.MenuMngService;

@Service
public class MenuMngServiceImpl implements MenuMngService {
	
	
	/** InquiryHistMapper Autowired. */
	@Autowired
	private MenuMngMapper menuMngMapper;
	/** MenuMngService Service  */
	MenuMngService menuMngService;
	@Override
	public List<Map<String, Object>> getMenuList(String lng) {
		List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
		int order = 0;
		
		List<MenuMngVO> menuList1 = menuMngMapper.getAuthList("1", "0", lng);
		for(MenuMngVO menu1 : menuList1){
			order++;
			Map<String,Object> menuData1 = new HashMap<String,Object>();
			menuData1.put("title", menu1.getMenuName());
			menuData1.put("isFolder", true);
			menuData1.put("key", menu1.getMenuNo());
			menuData1.put("expand", false);
			menuData1.put("order", order);
			menuData1.put("searchKey", menu1.getMenuNo()+menu1.getMenuName());
			menuData1.put("upMenuNo",0);
			menuData1.put("stepNo", menu1.getStepNo());
			menuData1.put("upMenuName", menu1.getUpMenuName());
			/*
			 * 2레벨 메뉴
			 */
			List<MenuMngVO> menuList2 = menuMngMapper.getAuthList("2", menu1.getMenuNo(), lng);
			List<Map<String,Object>> childMenuList2 = new ArrayList<Map<String,Object>>();
			for(MenuMngVO menu2 : menuList2){
				order++;
				Map<String,Object> menuData2 = new HashMap<String,Object>();
				menuData2.put("title", menu2.getMenuName());
				menuData2.put("isFolder", true);
				menuData2.put("key", menu2.getMenuNo());
				menuData2.put("expand", false);
				menuData2.put("order", order);
				menuData2.put("searchKey", menu2.getMenuNo()+menu2.getMenuName());
				menuData2.put("upMenuNo", menu2.getUpMenuNo());
				menuData2.put("stepNo", menu2.getStepNo());
				menuData2.put("upMenuName", menu2.getUpMenuName());
				childMenuList2.add(menuData2);
				
				/*
				 * 3레벨 메뉴
				 */
				List<MenuMngVO> menuList3 = menuMngMapper.getAuthList("3", menu2.getMenuNo(), lng);
				List<Map<String,Object>> childMenuList3 = new ArrayList<Map<String,Object>>();
				for(MenuMngVO menu3 : menuList3){
					order++;
					Map<String,Object> menuData3 = new HashMap<String,Object>();
					menuData3.put("title", menu3.getMenuName());
					menuData3.put("isFolder", true);
					menuData3.put("key", menu3.getMenuNo());
					menuData3.put("expand", false);
					menuData3.put("order", order);
					menuData3.put("searchKey", menu3.getMenuNo()+menu3.getMenuName());
					menuData3.put("upMenuNo", menu3.getUpMenuNo());
					menuData3.put("stepNo", menu3.getStepNo());
					menuData3.put("upMenuName", menu3.getUpMenuName());
					childMenuList3.add(menuData3);
					
					/*
					 * 4레벨 메뉴
					 */
					List<MenuMngVO> menuList4 = menuMngMapper.getAuthList("4", menu3.getMenuNo(), lng);
					List<Map<String,Object>> childMenuList4 = new ArrayList<Map<String,Object>>();
					for(MenuMngVO menu4 : menuList4){
						order++;
						Map<String,Object> menuData4 = new HashMap<String,Object>();
						menuData4.put("title", menu4.getMenuName());
						menuData4.put("isFolder", false);
						menuData4.put("key", menu4.getMenuNo());
						menuData4.put("expand", false);
						menuData4.put("order", order);
						menuData4.put("searchKey", menu4.getMenuNo()+menu4.getMenuName());
						menuData4.put("upMenuNo", menu4.getUpMenuNo());
						menuData4.put("stepNo", menu4.getStepNo());
						menuData4.put("upMenuName", menu4.getUpMenuName());
						childMenuList4.add(menuData4);
					}
					menuData3.put("children", childMenuList4);
					
				}
				menuData2.put("children", childMenuList3);
			}
			menuData1.put("children", childMenuList2);
			menuList.add(menuData1);
		}
		return menuList;
	}
	@Override
	public List<MenuMngVO> getDownMenuList(String lng, String condUpMenuNo,String topMenu,MenuMngVO menu
			,String sidx,String sord) {
		List<MenuMngVO> downMenuList=menuMngMapper.getDownMenuList(lng,condUpMenuNo,topMenu,menu,sidx,sord);
		
		for(MenuMngVO menu1 : downMenuList){
			menu1.setMenuLngList(menuMngMapper.getMenuLngList(menu1.getMenuNo(),lng));
		}
		return downMenuList;
	}
	@Override
	public List<Map<String, Object>> getLngListAction() {
		return menuMngMapper.getLngListAction();
	}
	@Override
	public void insertMenuAction(MenuMngVO menu) {
		int checkMenu = menuMngMapper.checkMenu(menu);
		if(checkMenu>0){
			throw new ServiceException( "MSG.M05.MSG00007" );
		}else{
			menuMngMapper.insertMenuAction(menu);
			
			String[] arrMenuNm=menu.getMenuNm().split(",");
			String[] arrCntryCd=menu.getCntryCd().split(",");
			String[] arrLngCd=menu.getLngCd().split(",");
			for(int i=0;i<arrMenuNm.length;i++){
				menu.setMenuNm(arrMenuNm[i]);
				menu.setCntryCd(arrCntryCd[i]);
				menu.setLngCd(arrLngCd[i]);
				menuMngMapper.insertLngMenu(menu);
			}
		}
		
	}
	@Override
	public void updateMenuAction(MenuMngVO menu) {
		menuMngMapper.updateMenuAction(menu);
		menuMngMapper.deleteMenuAction(menu.getMenuNo());
		
		String[] arrMenuNm=menu.getMenuNm().split(",");
		String[] arrCntryCd=menu.getCntryCd().split(",");
		String[] arrLngCd=menu.getLngCd().split(",");
		for(int i=0;i<arrMenuNm.length;i++){
			menu.setMenuNm(arrMenuNm[i]);
			menu.setCntryCd(arrCntryCd[i]);
			menu.setLngCd(arrLngCd[i]);
			menuMngMapper.insertLngMenu(menu);
		}
	}
	@Override
	public void deleteAction(MenuMngVO menu,String lng,String step) {
		if(step.equals("1")){
			List<MenuMngVO> menuList1=menuMngMapper.getMenuNoList(menu.getMenuNo());	//1level의 menu_no로 2level의 menu_no 가져오기
			
			for(MenuMngVO menu2 : menuList1){	
				List<MenuMngVO> menuList2=menuMngMapper.getMenuNoList(menu2.getMenuNo());	//2level의 menu_no로 3level menu_no 가져오기
				
				for(MenuMngVO menu3 : menuList2){	//4level의 up_menu_no == 3level의 menu_no 삭제
					System.err.println(menu3.getMenuNo());
					menuMngMapper.deleteMenuAuth(menu3.getMenuNo());	//4level auth delete
					menuMngMapper.deleteLastLng(menu3.getMenuNo());		//4level lng 삭제
					menuMngMapper.deleteUpAction(menu3.getMenuNo());	//4level menu delete
				}
				menuMngMapper.deleteMenuAuth(menu2.getMenuNo());	//3level auth delete
				menuMngMapper.deleteLastLng(menu2.getMenuNo());		//3level lng 삭제
				menuMngMapper.deleteUpAction(menu2.getMenuNo());	//3level menu delete
			}
			menuMngMapper.deleteMenuAuth(menu.getMenuNo());			//2level auth delete
			menuMngMapper.deleteLastLng(menu.getMenuNo());			//2level lng delete
			menuMngMapper.deleteUpAction(menu.getMenuNo());			//2level menu delete
			
			menuMngMapper.deleteLastMenuAuth(menu.getMenuNo());	//1level auth delete
			menuMngMapper.deleteMenuAction(menu.getMenuNo());	//1level lng delete
			menuMngMapper.deleteAction(menu.getMenuNo());		//1level menu delete
		}else if(step.equals("2")){
			List<MenuMngVO> menuList1=menuMngMapper.getMenuNoList(menu.getMenuNo());
			
			for(MenuMngVO menu2 : menuList1){
				menuMngMapper.deleteMenuAuth(menu2.getMenuNo());	//4level auth delete
				menuMngMapper.deleteLastLng(menu2.getMenuNo());		//4level lng 삭제
				menuMngMapper.deleteUpAction(menu2.getMenuNo());	//4level menu delete
			}
			menuMngMapper.deleteMenuAuth(menu.getMenuNo());		//3level auth delete
			menuMngMapper.deleteLastLng(menu.getMenuNo());		//3level lng 삭제
			menuMngMapper.deleteUpAction(menu.getMenuNo());		//3level menu delete

			menuMngMapper.deleteLastMenuAuth(menu.getMenuNo());	//2level auth delete
			menuMngMapper.deleteMenuAction(menu.getMenuNo());	//2level lng delete
			menuMngMapper.deleteAction(menu.getMenuNo());		//2level menu delete
			
		}else if(step.equals("3")){
			menuMngMapper.deleteMenuAuth(menu.getMenuNo());	//4level auth delete
			menuMngMapper.deleteLastLng(menu.getMenuNo());	//4level lng 삭제
			menuMngMapper.deleteUpAction(menu.getMenuNo());	//4level menu delete
			
			menuMngMapper.deleteLastMenuAuth(menu.getMenuNo());	//3level auth delete
			menuMngMapper.deleteMenuAction(menu.getMenuNo());	//3level lng 삭제
			menuMngMapper.deleteAction(menu.getMenuNo());	//3level menu delete
		}else if(step.equals("4")){
			menuMngMapper.deleteLastMenuAuth(menu.getMenuNo());	//4level auth delete
			menuMngMapper.deleteMenuAction(menu.getMenuNo());	//4level lng 삭제
			menuMngMapper.deleteAction(menu.getMenuNo());		//4level menu delete
		}
	}
}