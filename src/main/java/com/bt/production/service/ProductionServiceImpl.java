package com.bt.production.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bt.vo.ProcessManagementVo;
import com.bt.mapper.BranchSQLMapper;
import com.bt.mapper.ComponentStockSQLMapper;
import com.bt.mapper.DeliverySQLMapper;
import com.bt.mapper.DepartmentBoardSQLMapper;
import com.bt.mapper.DepartmentFileSQLMapper;
import com.bt.mapper.DepartmentboardreplySQLMapper;
import com.bt.mapper.EmployeeSQLMapper;
import com.bt.mapper.FactoryInItemSQLMapper;
import com.bt.mapper.FactoryOrderDetailSQLMapper;
import com.bt.mapper.FactoryOrderSQLMapper;
import com.bt.mapper.FactoryOutItemSQLMapper;
import com.bt.mapper.FactorySQLMapper;
import com.bt.mapper.ProcessListSQLMapper;
import com.bt.mapper.ProcessManagementSQLMapper;
import com.bt.mapper.ProcessStatusSQLMapper;
import com.bt.mapper.ProductComponentSQLMapper;
import com.bt.mapper.ProductFileSQLMapper;
import com.bt.mapper.ProductSQLMapper;
import com.bt.mapper.StoreOrderDetailSQLMapper;
import com.bt.mapper.StoreOrderReservationSQLMapper;
import com.bt.mapper.SupplierSQLMapper;
import com.bt.production.vo.ComponentStockVo;
import com.bt.production.vo.DeliveryVo;
import com.bt.production.vo.FactoryInItemVo;
import com.bt.production.vo.FactoryOrderDetailVo;
import com.bt.production.vo.FactoryOrderVo;
import com.bt.production.vo.FactoryOutItemVo;
import com.bt.vo.FactoryVo;
import com.bt.production.vo.ProductComponentVo;
import com.bt.production.vo.SupplierVo;
import com.bt.vo.ProcessListVo;
import com.bt.vo.ProcessStatusVo;
import com.bt.vo.ProductFileVo;
import com.bt.vo.StoreOrderDetailVo;
import com.bt.vo.StoreOrderReservationVo;
import com.bt.vo.BranchVo;
import com.bt.vo.DepartmentBoardVo;
import com.bt.vo.DepartmentFileVo;
import com.bt.vo.DepartmentboardreplyVo;
import com.bt.vo.EmployeeRankVo;
import com.bt.vo.EmployeeVo;
import com.bt.vo.ProductVo;

@Service
public class ProductionServiceImpl {

	@Autowired
	private EmployeeSQLMapper employeeSQLMapper;
	@Autowired
	private DepartmentBoardSQLMapper departmentBoardSQLMapper;
	@Autowired
	private DepartmentboardreplySQLMapper departmentboardreplySQLMapper;
	@Autowired
	private ComponentStockSQLMapper componentStockSQLMapper;
	@Autowired
	private BranchSQLMapper branchSQLMapper;
	@Autowired
	private ProductComponentSQLMapper productComponentSQLMapper;
	@Autowired
	private FactoryInItemSQLMapper factoryInItemSQLMapper;
	@Autowired
	private SupplierSQLMapper supplierSQLMapper;
	@Autowired
	private DepartmentFileSQLMapper departmentFileSQLMapper;
	@Autowired
	private FactoryOutItemSQLMapper factoryOutItemSQLMapper;
	@Autowired
	private ProductSQLMapper productSQLMapper;
	@Autowired
	private FactoryOrderSQLMapper factoryOrderSQLMapper;
	@Autowired
	private FactorySQLMapper factorySQLMapper;
	@Autowired
	private StoreOrderDetailSQLMapper storeOrderDetailSQLMapper;
	@Autowired
	private ProcessManagementSQLMapper processManagementSQLMapper;
	@Autowired
	private ProcessStatusSQLMapper processStatusSQLMapper;
	@Autowired
	private ProcessListSQLMapper processListSQLMapper;
	@Autowired
	private StoreOrderReservationSQLMapper storeOrderReservationSQLMapper;
	@Autowired
	private DeliverySQLMapper deliverySQLMapper;
	@Autowired
	private FactoryOrderDetailSQLMapper factoryOrderDetailSQLMapper;
	@Autowired
	private ProductFileSQLMapper productFileSQLMapper;

	// ??????????????? ??????
	public List<Map<String, Object>> getBoardList(String dept_title, String dept_content, String dept_writer, int currPage, int dept_type_no) {
        // ????????? ????????????
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<DepartmentBoardVo> boardList = null;

        if (dept_title == null && dept_content == null && dept_writer == null) {
           boardList = departmentBoardSQLMapper.selectAll(currPage, dept_type_no);
        } else {
           // ???????????? ??????
           boardList = departmentBoardSQLMapper.selectByTitle(dept_title,dept_content,dept_writer, currPage, dept_type_no);
        }

        for (DepartmentBoardVo departmentBoardVo : boardList) {
           EmployeeVo employeeVo = employeeSQLMapper.selectByCode(departmentBoardVo.getEmp_code());
           Map<String, Object> map = new HashMap<String, Object>();

           map.put("employeeVo", employeeVo);
           map.put("departmentBoardVo", departmentBoardVo);

           list.add(map);
        }

        return list;

     }

	public int getBoardCount(String dept_title, String dept_content, String dept_writer, int dept_type_no) {
		// ????????? ?????? ??????
		if (dept_title == null && dept_content == null && dept_writer == null) {
			// ?????????
			return departmentBoardSQLMapper.selectAllCount(dept_type_no);
		} else {
			// ???????????????
			return departmentBoardSQLMapper.selectByTitleCount(dept_title, dept_content, dept_writer, dept_type_no);
		}
	}

	public List<Map<String, Object>> getnoticeBoardList(String dept_title, String dept_content, String dept_writer,
			int currPage, int dept_type_no) {
		// ????????? ????????????
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<DepartmentBoardVo> boardList = null;

		if (dept_title == null && dept_content == null && dept_writer == null) {
			boardList = departmentBoardSQLMapper.selectnotice(currPage, dept_type_no);
		} else {
			// ???????????? ??????
			boardList = departmentBoardSQLMapper.selectnoticeByTitle(dept_title, dept_content, dept_writer, currPage,
					dept_type_no);
		}

		for (DepartmentBoardVo departmentBoardVo : boardList) {
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(departmentBoardVo.getEmp_code());
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("employeeVo", employeeVo);
			map.put("departmentBoardVo", departmentBoardVo);

			list.add(map);
		}

		return list;

	}

	public int createKey(DepartmentBoardVo departmentBoardVo) {
		int boardKey = departmentBoardSQLMapper.createKey();
		departmentBoardVo.setDept_board_no(boardKey);
		// System.out.println("????????? ?????? : "+boardKey);
		return boardKey;
	}

	public void writeContent(DepartmentBoardVo departmentBoardVo, List<DepartmentFileVo> fileVoList) {
	      // ????????? ??????
	      departmentBoardSQLMapper.insert(departmentBoardVo);
	   
	      // System.out.println("????????? ~ : "+departmentBoardVo.getDept_board_no());
	      for (DepartmentFileVo departmentFileVo : fileVoList) {
	         int dept_file_no=departmentFileSQLMapper.createKey();
	         departmentFileVo.setDept_file_no(dept_file_no);
	         departmentFileVo.setDept_board_no(departmentBoardVo.getDept_board_no());
	   
	         departmentFileSQLMapper.insert(departmentFileVo);
	      }
	   }

	public void updateCheck(int dept_board_no, int emp_code) {
		// ???????????? ????????????
		departmentBoardSQLMapper.updateCheck(dept_board_no, emp_code);
	}

	public Map<String, Object> readContent(int dept_board_no, int dept_no) {
	      // ????????? ??????
	      Map<String, Object> map = new HashMap<String, Object>();

	      DepartmentBoardVo departmentBoardVo = departmentBoardSQLMapper.selectByNo(dept_board_no, dept_no);
	      EmployeeVo employeeVo = employeeSQLMapper.selectByCode(departmentBoardVo.getEmp_code());
	      EmployeeRankVo employeeRankVo = employeeSQLMapper.selectByEmpRankNo(employeeVo.getRank_no());
	      BranchVo branchVo = branchSQLMapper.selectByBNO(employeeVo.getBranch_no());
	      List<DepartmentFileVo> departmentFileVolist = departmentFileSQLMapper.selectByBoardNo(dept_board_no);
	      
	      map.put("departmentBoardVo", departmentBoardVo);
	      map.put("employeeVo", employeeVo);
	      map.put("employeeRankVo", employeeRankVo);
	      map.put("branchVo", branchVo);
	      map.put("departmentFileVolist",departmentFileVolist);

	      return map;
	   }

	public Map<String, Object> updateReadContent(int dept_board_no, int dept_type_no, int emp_code) {
		// ????????? ??? ????????? ??????
		Map<String, Object> map = new HashMap<String, Object>();

		DepartmentBoardVo departmentBoardVo = departmentBoardSQLMapper.selectByNoAndCode(dept_board_no, dept_type_no,
				emp_code);
		EmployeeVo employeeVo = employeeSQLMapper.selectByCode(departmentBoardVo.getEmp_code());
		EmployeeRankVo employeeRankVo = employeeSQLMapper.selectByEmpRankNo(employeeVo.getRank_no());
		BranchVo branchVo = branchSQLMapper.selectByBNO(employeeVo.getBranch_no());
		List<DepartmentFileVo> departmentFileVolist = departmentFileSQLMapper.selectByBoardNo(dept_board_no);

		map.put("departmentBoardVo", departmentBoardVo);
		map.put("employeeVo", employeeVo);
		map.put("employeeRankVo", employeeRankVo);
		map.put("branchVo", branchVo);
		map.put("departmentFileVolist", departmentFileVolist);

		return map;
	}

	public void updateRead(int dept_board_no, int emp_code, int dept_type_no) {
		// ????????? ????????????
		DepartmentBoardVo departmentBoardVo = departmentBoardSQLMapper.selectByNo(dept_board_no, dept_type_no);
		EmployeeVo employeeVo = employeeSQLMapper.selectByEmpCode(departmentBoardVo.getEmp_code());
		if (!(emp_code == employeeVo.getEmp_code())) {
			departmentBoardSQLMapper.updateView(dept_board_no);
		}
	}

	public void update(int dept_board_no, String dept_board_title, String dept_board_content, int emp_code) {
		// ????????? ??????
		departmentBoardSQLMapper.update(dept_board_no, dept_board_title, emp_code, dept_board_content);
	}

	// ?????? ?????? ??????
	public void updatefile(DepartmentBoardVo departmentBoardVo, List<DepartmentFileVo> fileVoList) {
		departmentFileSQLMapper.deletebyboardno(departmentBoardVo.getDept_board_no());
		// System.out.println("????????? ~ : "+departmentBoardVo.getDept_board_no());
	    for (DepartmentFileVo departmentFileVo : fileVoList) {
	    	int dept_file_no=departmentFileSQLMapper.createKey();
	        departmentFileVo.setDept_file_no(dept_file_no);
	        departmentFileVo.setDept_board_no(departmentBoardVo.getDept_board_no());
	            
	        departmentFileSQLMapper.insert(departmentFileVo);
	        }
	}

	public void delete(int dept_board_no, int emp_code) {
		departmentBoardSQLMapper.delete(dept_board_no, emp_code);
		departmentboardreplySQLMapper.deletebyboardno(dept_board_no);
		departmentFileSQLMapper.deletebyboardno(dept_board_no);
	}

	public void writeReply(DepartmentboardreplyVo departmentboardreplyVo) {
		int dept_board_reply_no = departmentboardreplySQLMapper.replyCreatekey();
		departmentboardreplyVo.setDept_board_reply_no(dept_board_reply_no);
		departmentboardreplySQLMapper.insert(departmentboardreplyVo);
	}

	public void deleteReply(int dept_board_reply_no) {
		departmentboardreplySQLMapper.delete(dept_board_reply_no);
	}
	public int getempcodebyreplyno(int dept_board_reply_no) {
  	  return departmentboardreplySQLMapper.getempcodebyreplyno(dept_board_reply_no);
    }

	public List<Map<String, Object>> getReplyList(int board_no) {

		List<Map<String, Object>> list = new ArrayList<>();
		List<DepartmentboardreplyVo> replyVoList = departmentboardreplySQLMapper.selectByBoardNo(board_no);

		for (DepartmentboardreplyVo departmentboardreplyVo : replyVoList) {
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(departmentboardreplyVo.getEmp_code());
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("employeeVo", employeeVo);
			map.put("departmentboardreplyVo", departmentboardreplyVo);

			list.add(map);
		}

		return list;
	}

	public void updatereply(int dept_board_reply_no, String dept_board_reply_content) {
		departmentboardreplySQLMapper.update(dept_board_reply_no, dept_board_reply_content);
	}
	// ??????????????? ???

	// ???????????? ??????
	public List<Map<String, Object>> getComponentList(int branch_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<ComponentStockVo> componentStockList = componentStockSQLMapper.selectByBn(branch_no);

		for (ComponentStockVo componentStockVo : componentStockList) {
			ProductComponentVo productComponentVo = productComponentSQLMapper.selectCompNo(componentStockVo.getComp_no());
			BranchVo branchVo = branchSQLMapper.selectByBNO(componentStockVo.getBranch_no());
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("productComponentVo", productComponentVo);
			map.put("branchVo", branchVo);
			map.put("componentStockVo", componentStockVo);

			list.add(map);
		}

		return list;
	}
	// ???????????? ???

	// ?????? ???????????? ??????
	public void insertComponent(String comp_name, String comp_price, int comp_qty, int branch_no) {
		// ????????????
		int key = productComponentSQLMapper.createKey();
		System.out.println("key: " + key);

		if (comp_qty == 0) {
			// ??????????????????
			productComponentSQLMapper.insert(key, comp_name, comp_price);
		} else {
			// ??????????????????
			productComponentSQLMapper.insert(key, comp_name, comp_price);
			// ??????????????????
			System.out.println("key ?????? : " + key);
			componentStockSQLMapper.insert(branch_no, key, comp_qty);
		}

	}

	// ?????? ?????? ????????????
	public ProductComponentVo selectByCompName(String comp_name) {
		ProductComponentVo productComponentVo = productComponentSQLMapper.selectCompName(comp_name);
		return productComponentVo;
	}
	// ?????? ???????????? ???

	// ???????????? ??????
	public List<Map<String, Object>> getInItem(int branch_no, int factory_in_item_code, int comp_no, String start_date,
			String end_date, int emp_code, int supplier_no, int currPage, String sort) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<FactoryInItemVo> factoryInItemList = null;

		if (factory_in_item_code == 0 && comp_no == 0 && start_date == null && end_date == null && emp_code == 0 && supplier_no == 0) {
			factoryInItemList = factoryInItemSQLMapper.selectAll(branch_no, currPage, sort);
		} else {
			factoryInItemList = factoryInItemSQLMapper.selectInItem(factory_in_item_code, comp_no, start_date, end_date, emp_code, supplier_no, branch_no, currPage, sort);
		}

		for (FactoryInItemVo factoryInItemVo : factoryInItemList) {
			FactoryOrderDetailVo factoryOrderDetailVo = factoryOrderDetailSQLMapper.selectByFodn(factoryInItemVo.getFactory_order_detail_no());
			ProductComponentVo productComponentVo = productComponentSQLMapper.selectCompNo(factoryOrderDetailVo.getComp_no());
			SupplierVo supplierVo = supplierSQLMapper.selectBySn(factoryOrderDetailVo.getSupplier_no());
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(factoryInItemVo.getEmp_code());

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("productComponentVo", productComponentVo);
			map.put("factoryInItemVo", factoryInItemVo);
			map.put("employeeVo", employeeVo);
			map.put("supplierVo", supplierVo);
			map.put("factoryOrderDetailVo", factoryOrderDetailVo);

			list.add(map);
		}

		return list;
	}

	public List<Map<String, Object>> allSupplier() {
		// ?????? ???????????? ????????? ????????????
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<SupplierVo> supplierList = supplierSQLMapper.selectAll();

		for (SupplierVo supplierVo : supplierList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("supplierVo", supplierVo);

			list.add(map);
		}
		return list;
	}

	public List<SupplierVo> supplierList(int comp_no) {
		// ?????? ????????? ?????? ?????????????????????
		List<SupplierVo> supplierList = supplierSQLMapper.selectByCompNo(comp_no);
		return supplierList;
	}

	public List<Map<String, Object>> empList(int branch_no) {
		// ?????? ????????? ?????? ???????????????
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<EmployeeVo> empList = employeeSQLMapper.selectEmp(branch_no);

		for (EmployeeVo employeeVo : empList) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("employeeVo", employeeVo);

			list.add(map);
		}

		return list;
	}

	public int getInItemListCount(int factory_in_item_code, int comp_no, String start_date, String end_date,
			int emp_code, int supplier_no, int branch_no) {
		// ?????????
		if (factory_in_item_code == 0 && comp_no == 0 && start_date == null && end_date == null && emp_code == 0 && supplier_no == 0) {
			return factoryInItemSQLMapper.selectAllCount(branch_no);
		} else {
			return factoryInItemSQLMapper.selectSearchCount(factory_in_item_code, comp_no, start_date, end_date, emp_code, supplier_no, branch_no);
		}

	}
	// ???????????? ???

	// ???????????? ??????
	public List<Map<String, Object>> getNoneOrder(int branch_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<FactoryOrderDetailVo> noneOrderList = factoryOrderDetailSQLMapper.getNoneOrder(branch_no);

		for (FactoryOrderDetailVo factoryOrderDetailVo : noneOrderList) {
			ProductComponentVo productComponentVo = productComponentSQLMapper.selectCompNo(factoryOrderDetailVo.getComp_no());

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("factoryOrderDetailVo", factoryOrderDetailVo);
			map.put("productComponentVo", productComponentVo);

			list.add(map);
		}

		return list;
	}

	public void addComponent(int[] factory_order_detail_no, int[] factory_in_item_qty, int emp_code) {
		// ?????? ???????????? insert??????
		for (int i = 0; i < factory_order_detail_no.length; i++) {
			factoryInItemSQLMapper.insert(factory_order_detail_no[i], factory_in_item_qty[i], emp_code);
		}

	}

	public void updateComponent(int[] factory_in_item_qty, int[] comp_no) {
		// ?????? ?????? ????????????
		for (int i = 0; i < comp_no.length; i++) {
			componentStockSQLMapper.update(factory_in_item_qty[i], comp_no[i]);
		}
	}

	public void updateCheckApproval(int[] factory_order_detail_no) {
		// ???????????? Y??? ?????????
		for (int i = 0; i < factory_order_detail_no.length; i++) {
			factoryOrderDetailSQLMapper.update(factory_order_detail_no[i]);
		}
	}
	// ???????????? ???

	// ???????????? ??????
	public List<Map<String, Object>> getOutItem(int factory_out_item_no, int product_no, int branch_no,
			String start_date, String end_date, int session_branch_no, int currPage, String sort) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<FactoryOutItemVo> factoryOutItemList = null;

		if (factory_out_item_no == 0 && product_no == 0 && branch_no == 0 && start_date == null && end_date == null) {
			factoryOutItemList = factoryOutItemSQLMapper.selectAll(session_branch_no, currPage, sort);
		} else {
			factoryOutItemList = factoryOutItemSQLMapper.selectOutItem(factory_out_item_no, product_no, branch_no, start_date, end_date, session_branch_no, currPage, sort);
		}

		for (FactoryOutItemVo factoryOutItemVo : factoryOutItemList) {
			BranchVo branchVo = branchSQLMapper.selectByBNO(factoryOutItemVo.getBranch_no());
			ProductVo productVo = productSQLMapper.selectByPn(factoryOutItemVo.getProduct_no());
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(factoryOutItemVo.getEmp_code());
			
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("branchVo", branchVo);
			map.put("productVo", productVo);
			map.put("factoryOutItemVo", factoryOutItemVo);
			map.put("employeeVo", employeeVo);

			list.add(map);
		}

		return list;
	}

	public int getOutItemListCount(int factory_out_item_no, int product_no, int branch_no, String start_date,
			String end_date, int session_branch_no) {
		// ?????????
		if (factory_out_item_no == 0 && product_no == 0 && branch_no == 0 && start_date == null && end_date == null) {
			return factoryOutItemSQLMapper.selectAllCount(session_branch_no);
		} else {
			return factoryOutItemSQLMapper.selectSearchCount(factory_out_item_no, product_no, branch_no, start_date, end_date, session_branch_no);
		}

	}
	// ???????????? ???

	// ???????????? ??????
	public void addoutProduct(int factory_out_item_no, int product_no, int factory_out_item_qty, int emp_code, String factory_out_item_date, int branch_no) {
		int outItemKey = factoryOutItemSQLMapper.createKey();
		factoryOutItemSQLMapper.insert(outItemKey, factory_out_item_no, product_no, factory_out_item_qty, emp_code, factory_out_item_date, branch_no);
	}

	public Map<String, Object> getFactoryNo(int emp_code) {
		Map<String, Object> map = new HashMap<String, Object>();

		FactoryVo factoryVo = factorySQLMapper.selectbyFn(emp_code);
		map.put("factoryVo", factoryVo);

		return map;
	}

	public List<Map<String, Object>> productList(int emp_code) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<ProductVo> productList = productSQLMapper.selectPTN(emp_code);

		for (ProductVo productVo : productList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productVo", productVo);

			list.add(map);
		}

		return list;
	}

	public List<Map<String, Object>> branchList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<BranchVo> branchList = branchSQLMapper.selectByBTN();

		for (BranchVo branchVo : branchList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("branchVo", branchVo);

			list.add(map);
		}

		return list;
	}

	public void updateCompStock(int factory_out_item_qty) {
		// ?????????????????? ?????? ??????
		componentStockSQLMapper.minusComp(factory_out_item_qty);
	}
	// ?????? ?????? ???

	// ?????? ?????? ??????
	public int getSequence() {
		// ?????? ????????? ????????? ????????????
		return factoryOrderSQLMapper.maxSeq();
	}

	// ?????? ????????????
	public void componentOrderDetail(FactoryOrderDetailVo factoryOrderDetailVo, FactoryOrderVo factoryOrderVo,
			String factory_order_res_no, int emp_code, int[] comp_no, int[] supplier_no, int[] factory_order_qty,
			String factory_order_note) {
		// factory_order insert ??????
		int factory_order_code = factoryOrderSQLMapper.createKey();
		factoryOrderVo.setFactory_order_code(factory_order_code);

		System.out.println("?????? ?????? ????????????????????? : " + factoryOrderVo.getFactory_order_code());

		factoryOrderSQLMapper.insert(factory_order_code, factory_order_res_no, emp_code);

		// factory_order_detail insert ??????
		factoryOrderDetailVo.setFactory_order_code(factoryOrderVo.getFactory_order_code());
		System.out.println("detail_????????????????????? : " + factoryOrderDetailVo.getFactory_order_code());

		for (int i = 0; i < comp_no.length; i++) {
			System.out.println("?????? : " + comp_no[i]);
			System.out.println("?????? : " + supplier_no[i]);
			System.out.println("?????? : " + factory_order_qty[i]);

			factoryOrderDetailSQLMapper.insert(factory_order_code, comp_no[i], supplier_no[i], factory_order_qty[i], factory_order_note);
		}

	}

	// ?????? ?????? ????????? ????????????
	public List<Map<String, Object>> compList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<ProductComponentVo> compList = productComponentSQLMapper.selectComp();

		for (ProductComponentVo productComponentVo : compList) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("productComponentVo", productComponentVo);

			list.add(map);

		}

		return list;
	}
	// ?????? ?????? ???

	// ?????? ?????? ??????
	public List<Map<String, Object>> getOrderList(int branch_no, String factory_order_res_no, int emp_code, String start_date, String end_date, int currPage, String sort) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<FactoryOrderVo> orderList = null;

		if (factory_order_res_no == null && emp_code == 0 && start_date == null && end_date == null) {
			orderList = factoryOrderSQLMapper.selectAll(branch_no, currPage, sort);
		} else {
			orderList = factoryOrderSQLMapper.searchOrder(branch_no, factory_order_res_no, emp_code, start_date, end_date, currPage, sort);
		}

		for (FactoryOrderVo factoryOrderVo : orderList) {
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(factoryOrderVo.getEmp_code());

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("factoryOrderVo", factoryOrderVo);
			map.put("employeeVo", employeeVo);

			list.add(map);
		}

		return list;
	}

	public int getOrderListCount(int branch_no, String factory_order_res_no, int emp_code, String start_date, String end_date) {
		// ?????????
		if (factory_order_res_no == null && emp_code == 0 && start_date == null && end_date == null) {
			return factoryOrderSQLMapper.selectAllCount(branch_no);
		} else {
			return factoryOrderSQLMapper.selectSearchCount(branch_no, factory_order_res_no, emp_code, start_date, end_date);
		}

	}

	// ?????? ???????????? ?????????
	public List<Map<String, Object>> getOrder(int factory_order_code) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<FactoryOrderDetailVo> orderDetailList = factoryOrderDetailSQLMapper.getOrderDetail(factory_order_code);

		for (FactoryOrderDetailVo factoryOrderDetailVo : orderDetailList) {
			FactoryOrderVo factoryOrderVo = factoryOrderSQLMapper.selectByFoc(factoryOrderDetailVo.getFactory_order_code());
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(factoryOrderVo.getEmp_code());
			ProductComponentVo productComponentVo = productComponentSQLMapper.selectCompNo(factoryOrderDetailVo.getComp_no());
			SupplierVo supplierVo = supplierSQLMapper.selectBySn(factoryOrderDetailVo.getSupplier_no());

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("factoryOrderDetailVo", factoryOrderDetailVo);
			map.put("factoryOrderVo", factoryOrderVo);
			map.put("employeeVo", employeeVo);
			map.put("productComponentVo", productComponentVo);
			map.put("supplierVo", supplierVo);

			list.add(map);

		}

		return list;
	}
	// ?????? ?????? ???

	// ?????? ?????? ??????
	public List<Map<String, Object>> reqDelivery(int branch_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<StoreOrderDetailVo> detailList = storeOrderDetailSQLMapper.detailList(branch_no);

		for (StoreOrderDetailVo storeOrderDetailVo : detailList) {
			Map<String, Object> map = new HashMap<String, Object>();

			ProductVo productVo = productSQLMapper.selectByPn(storeOrderDetailVo.getProduct_no());
			ProcessManagementVo processManagementVo = processManagementSQLMapper.selectByDn(storeOrderDetailVo.getStore_order_detail_no());
			ProcessStatusVo processStatusVo = processStatusSQLMapper.selectByODn(storeOrderDetailVo.getStore_order_detail_no());
			ProcessListVo processListVo = processListSQLMapper.selectByPrNo(processStatusVo.getProcess_no());
			StoreOrderReservationVo storeOrderReservationVo = storeOrderReservationSQLMapper.selectByno(storeOrderDetailVo.getStore_order_res_code());
			EmployeeVo employeeVo = employeeSQLMapper.selectByEmpCode(storeOrderReservationVo.getEmp_code());
			BranchVo branchVo = branchSQLMapper.selectByBNO(employeeVo.getBranch_no());

			map.put("productVo", productVo);
			map.put("processManagementVo", processManagementVo);
			map.put("storeOrderDetailVo", storeOrderDetailVo);
			map.put("processStatusVo", processStatusVo);
			map.put("processListVo", processListVo);
			map.put("branchVo", branchVo);

			list.add(map);
		}

		return list;
	}

	// ???????????? ??????
	public void changeState(ProcessStatusVo processStatusVo) {
		processStatusSQLMapper.insert(processStatusVo);
	}

	// ???????????? ??????
	public void rejectState(ProcessStatusVo processStatusVo) {
		processStatusSQLMapper.reject(processStatusVo);
	}
	// ?????? ?????? ???

	// ???????????? ??????
	// ??????????????? ?????? ????????????
	public List<Map<String, Object>> getReadyDelivery(int branch_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<StoreOrderDetailVo> getList = storeOrderDetailSQLMapper.readyDelivery(branch_no);

		for (StoreOrderDetailVo storeOrderDetailVo : getList) {
			ProcessStatusVo processStatusVo = processStatusSQLMapper.getProcessNo(storeOrderDetailVo.getStore_order_detail_no());
			ProcessManagementVo processManagementVo = processManagementSQLMapper.selectByDn(storeOrderDetailVo.getStore_order_detail_no());
			ProcessListVo processListVo = processListSQLMapper.selectByPrNo(processStatusVo.getProcess_no());
			StoreOrderReservationVo storeOrderReservationVo = storeOrderReservationSQLMapper.selectByno(storeOrderDetailVo.getStore_order_res_code());
			EmployeeVo employeeVo = employeeSQLMapper.selectByEmpCode(storeOrderReservationVo.getEmp_code());
			BranchVo branchVo = branchSQLMapper.selectByBNO(employeeVo.getBranch_no());
			ProductVo productVo = productSQLMapper.selectByPn(storeOrderDetailVo.getProduct_no());
			
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("storeOrderDetailVo", storeOrderDetailVo);
			map.put("processManagementVo", processManagementVo);
			map.put("processStatusVo", processStatusVo);
			map.put("processListVo", processListVo);
			map.put("branchVo", branchVo);
			map.put("productVo", productVo);
			
			list.add(map);
		}

		return list;
	}

	public Map<String, Object> getEnroll(int store_order_detail_no) {
		Map<String, Object> map = new HashMap<String, Object>();

		ProcessManagementVo processManagementVo = processManagementSQLMapper.selectByDn(store_order_detail_no);
		BranchVo branchVo = branchSQLMapper.selectBySodn(store_order_detail_no);
		ProductVo productVo = productSQLMapper.selectBySodn(store_order_detail_no);

		map.put("processManagementVo", processManagementVo);
		map.put("branchVo", branchVo);
		map.put("productVo", productVo);

		return map;
	}

	public void enrollDelivery(DeliveryVo deliveryVo, ProcessStatusVo processStatusVo) {
		deliverySQLMapper.insert(deliveryVo);
		processStatusSQLMapper.insertDelivery(processStatusVo);
	}
	// ???????????? ???

	// ?????? ?????? ??????
	public List<Map<String, Object>> getDeliveryList(int store_order_detail_no, int invoice_no, String start_date, String end_date,
			int currPage, int session_branch_no, int branch_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<DeliveryVo> deliveryList = null;

		if (store_order_detail_no == 0 && invoice_no == 0 && start_date == null && end_date == null && branch_no == 0) {
			deliveryList = deliverySQLMapper.selectAll(session_branch_no, currPage);
		} else {
			deliveryList = deliverySQLMapper.selectBySearch(store_order_detail_no, invoice_no, start_date, end_date, session_branch_no, branch_no, currPage);
		}
		for (DeliveryVo deliveryVo : deliveryList) {
			Map<String, Object> map = new HashMap<String, Object>();

			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(deliveryVo.getEmp_code());
			BranchVo branchVo = branchSQLMapper.selectBySodn(deliveryVo.getStore_order_detail_no());

			map.put("deliveryVo", deliveryVo);
			map.put("employeeVo", employeeVo);
			map.put("branchVo", branchVo);

			list.add(map);
		}

		return list;
	}

	public int getDeliveryListCount(int store_order_detail_no, int invoice_no, String start_date, String end_date, int currPage,
			int session_branch_no, int branch_no) {
		// ?????????
		if (store_order_detail_no == 0 && invoice_no == 0 && start_date == null && end_date == null && branch_no == 0) {
			return deliverySQLMapper.selectAllCount(session_branch_no);
		} else {
			return deliverySQLMapper.selectSearchCount(store_order_detail_no, invoice_no, start_date, end_date,
					session_branch_no, branch_no);
		}

	}

	// ??????????????????
	public List<Map<String, Object>> readDeliveryList(int store_order_detail_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<DeliveryVo> deliveryList = deliverySQLMapper.selectBySodn(store_order_detail_no);
		ProcessListVo processListVo = processListSQLMapper.selectByMax(store_order_detail_no);

		for (DeliveryVo deliveryVo : deliveryList) {
			StoreOrderDetailVo storeOrderDetailVo = storeOrderDetailSQLMapper
					.selectBySodn(deliveryVo.getStore_order_detail_no());
			StoreOrderReservationVo storeOrderReservationVo = storeOrderReservationSQLMapper
					.selectByno(storeOrderDetailVo.getStore_order_res_code());
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(deliveryVo.getEmp_code());
			EmployeeVo employee = employeeSQLMapper.selectByCode(storeOrderReservationVo.getEmp_code());
			BranchVo branchVo = branchSQLMapper.selectByBNO(employee.getBranch_no());
			ProductVo productVo = productSQLMapper.selectByPn(storeOrderDetailVo.getProduct_no());
			ProductFileVo productFileVo = productFileSQLMapper.selectByProductNo(productVo.getProduct_no()); 
					
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("employeeVo", employeeVo);
			map.put("deliveryVo", deliveryVo);
			map.put("branchVo", branchVo);
			map.put("productVo", productVo);
			map.put("processListVo", processListVo);
			map.put("productFileVo", productFileVo);
			
			list.add(map);
		}

		return list;
	}
	// ?????? ?????? ???

	// ?????? ???????????? ??????
	public List<Map<String, Object>> productOrderList(int currPage, int branch_no, String store_order_res_no,
			String branch_name, String start_date, String end_date) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<StoreOrderDetailVo> productOrderList = storeOrderDetailSQLMapper.selectAllOrder(currPage, branch_no,
				store_order_res_no, branch_name, start_date, end_date);

		for (StoreOrderDetailVo storeOrderDetailVo : productOrderList) {
			StoreOrderReservationVo storeOrderReservationVo = storeOrderReservationSQLMapper
					.selectByno(storeOrderDetailVo.getStore_order_res_code());
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(storeOrderReservationVo.getEmp_code());
			BranchVo branchVo = branchSQLMapper.selectByBNO(employeeVo.getBranch_no());
			
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("storeOrderReservationVo", storeOrderReservationVo);
			map.put("branchVo", branchVo);
		
			list.add(map);
		}

		return list;
	}

	// ?????? ??????????????? ?????????
	public int getListCount(int branch_no, String store_order_res_no, String branch_name, String start_date,
			String end_date) {

		if (store_order_res_no == null && branch_name == null && start_date == null && end_date == null) {
			return storeOrderDetailSQLMapper.getAllCount(branch_no);
		} else {
			return storeOrderDetailSQLMapper.getSelectCount(branch_no, store_order_res_no, branch_name, start_date,
					end_date);
		}

	}

	// ?????? ?????? ???????????? ??????
	public List<Map<String, Object>> productOrderDetailList(int branch_no, int store_order_res_code) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<StoreOrderDetailVo> productOrderList = storeOrderDetailSQLMapper.selectBySORC(branch_no,
				store_order_res_code);

		for (StoreOrderDetailVo storeOrderDetailVo : productOrderList) {
			StoreOrderReservationVo storeOrderReservationVo = storeOrderReservationSQLMapper
					.selectByno(storeOrderDetailVo.getStore_order_res_code());
			EmployeeVo employeeVo = employeeSQLMapper.selectByCode(storeOrderReservationVo.getEmp_code());
			BranchVo branchVo = branchSQLMapper.selectByBNO(employeeVo.getBranch_no());
			ProductVo productVo = productSQLMapper.selectByPn(storeOrderDetailVo.getProduct_no());
			ProcessManagementVo processManagementVo = processManagementSQLMapper
					.selectByDn(storeOrderDetailVo.getStore_order_detail_no());
			EmployeeVo employeeVo2 = employeeSQLMapper.selectByCode(processManagementVo.getEnd_emp_code());
			ProcessListVo processListVo = processListSQLMapper.selectByMax(storeOrderDetailVo.getStore_order_detail_no());
			
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("storeOrderDetailVo", storeOrderDetailVo);
			map.put("storeOrderReservationVo", storeOrderReservationVo);
			map.put("branchVo", branchVo);
			map.put("productVo", productVo);
			map.put("processManagementVo", processManagementVo);
			map.put("employeeVo2", employeeVo2);
			map.put("processListVo", processListVo);
	
			list.add(map);
		}

		return list;
	}
	// ?????? ???????????? ???

	// ???????????? to do list
	public int countDoList(int branch_no) {
		// ?????? ????????? ?????? ??????
		int orderCount = factoryOrderDetailSQLMapper.selectOrderCount(branch_no);

		return orderCount;
	}

	public int CountProductList(int branch_no) {
		// ?????? ???????????? ????????? ?????? ??????
		int productCount = storeOrderDetailSQLMapper.getRequestCount(branch_no);

		return productCount;
	}

	public int CountReadyList(int branch_no) {

		int readyCount = storeOrderDetailSQLMapper.getreadyCount(branch_no);

		return readyCount;
	}
}