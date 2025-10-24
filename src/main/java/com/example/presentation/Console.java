package com.example.presentation;

import com.example.presentation.model.*;
import com.example.presentation.model.Process;
import com.example.presentation.runing.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
@Component
public class Console implements CommandLineRunner {
    private final Scanner sc = new Scanner(System.in);
    private final EmployeeDao employeeDao;
    private final ProductDao productDao;
    private final WasteDao wasteDao;
    private final S1WastDao s1WastDao;
    private final S2StepDao s2StepDao;
    private final S3ProductionDao s3ProductionDao;
    private final OperationsDao operationsDao;
    private final MaterialDao materialDao;
    private final PerformDao performDao;
    private final InventoryDao inventoryDao;


    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("====== 콘솔 공장 관리 시스템 ======");
            System.out.println("[1]기준 정보 관리 [2]생산 관리 [3]자재/설비 관리 [4]폐기 관리 [5]종료");
            System.out.println("[6]폐기 관련 조회 [7]각 단계별 제품 및 종료시간 조회 [8]제품 관련 재료, 공정, 작업자 조회");
            int choice1 = sc.nextInt();
            sc.nextLine();
            switch (choice1) {
                // 기준 정보 관리
                case 1:
                    System.out.println("[1]상품 관리 [2]직원 관리 [3]종료");
                    int choice2 = sc.nextInt();
                    sc.nextLine();
                    switch (choice2) {
                        // 상품 관리
                        case 1:
                            System.out.println("[1]상품 전체 조회 [2]상품 등록 [3]상품 정보 수정 [4]상품 정보 삭제 [5]종료");
                            int choice3 = sc.nextInt();
                            sc.nextLine();
                            switch (choice3) {
                                case 1:
                                    List<Product> productList = productDao.productList();
                                    System.out.println("======== 상품 목록 조회 =========");
                                    for (Product product : productList) System.out.println(product);
                                    break;
                                case 2:
                                    boolean isSuccess = productDao.insertproduct(productDao.regProduct());
                                    System.out.println("상품 등록 : " + (isSuccess ? "성공" : "실패"));
                                    break;
                                case 3:
                                    boolean isUpdate = productDao.updateProduct();
                                    System.out.println("상품 정보 수정 : " + (isUpdate ? "성공" : "실패"));
                                    break;
                                case 4:
                                    boolean isDelete = productDao.removeProduct();
                                    System.out.println("상품 삭제 : " + (isDelete ? "성공" : "실패"));
                                    break;
                                case 5:
                                    break;
                            }
                            break;
                        // 직원 관리
                        case 2:
                            System.out.println("[1]직원 전체 조회 [2]직원 등록 [3]직원 정보 수정 [4]직원 정보 삭제 [5]종료");
                            int choice4 = sc.nextInt();
                            sc.nextLine();
                            switch (choice4) {
                                case 1:
                                    List<Employee> employeeList = employeeDao.employeeList();
                                    System.out.println("======== 직원 목록 조회 =========");
                                    for (Employee employee : employeeList) System.out.println(employee);
                                    break;
                                case 2:
                                    boolean isSuccess = employeeDao.insertemployee(employeeDao.regEmployee());
                                    System.out.println("직원 등록 : " + (isSuccess ? "성공" : "실패"));
                                    break;
                                case 3:
                                    boolean isUpdate = employeeDao.updateEmployee();
                                    System.out.println("직원 정보 수정 : " + (isUpdate ? "성공" : "실패"));
                                    break;
                                case 4:
                                    boolean isDelete = employeeDao.removeEmployee();
                                    System.out.println("직원삭제 : " + (isDelete ? "성공" : "실패"));
                                    break;
                                case 5:
                                    return;
                            }
                            break;
                        case 3:
                            return;
                    }
                    break;
                // 생산 관리 (지시 삭제가 없습니다.)
                case 2:
                    System.out.println("[1]작업지시 등록 [2]작업지시 상태 변경 [3]작업지시 조회 [4]공정 조회 [5]장비 조회 [6]종료");
                    int prodChoice = sc.nextInt();
                    sc.nextLine();
                    switch (prodChoice) {
                        case 1:
                            boolean isSuccessWO = operationsDao.insertWorkOrder(operationsDao.regWorkOrder());
                            System.out.println("작업지시 등록 : " + (isSuccessWO ? "성공" : "실패"));
                            break;
                        case 2:
                            boolean isUpdateWO = operationsDao.updateWorkOrderStatus();
                            System.out.println("작업지시 상태 변경 : " + (isUpdateWO ? "성공" : "실패"));
                            break;
                        case 3:
                            List<WorkOrder> workOrders = operationsDao.findAllWorkOrders();
                            System.out.println("======== 작업지시 조회 =========");
                            for (WorkOrder wo : workOrders) System.out.println(wo);
                            break;
                        case 4:
                            List<Process> processes = operationsDao.findAllProcesses();
                            System.out.println("======== 공정 조회 =========");
                            for (Process p : processes) System.out.println(p);
                            break;
                        case 5:
                            List<Equipment> equipments = operationsDao.findAllEquipments();
                            System.out.println("======== 장비 조회 =========");
                            for (Equipment e : equipments) System.out.println(e);
                            break;
                        case 6:
                            break; // 하위 메뉴 종료, 상위 메뉴 반복
                    }
                    break;
                // 자재/설비 관리
                case 3:
                    System.out.println(("=").repeat(7) + " 자재 관리 시스템 " + ("=").repeat(7));
                    System.out.println("[1] 자재 등록 [2] 자재 수정 [3] 자재 삭제 [4] 유통기한 조회 [5] 자재 전체 조회");
                    System.out.println("[6] 창고 재고 조회 [7] 창고 재고 입고 [8] 창고 재고 출고 [9] 설비 상태 조회");
                    System.out.println("[0] 종료");
                    System.out.print("선택> ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1:
                            registerMaterial();
                            break;
                        case 2:
                            updateMaterial();
                            break;
                        case 3:
                            deleteMaterial();
                            break;
                        case 4:
                            findExpiringMaterials();
                            break;
                        case 5:
                            materialList();
                            break;
                        case 6:
                            inventList();
                            break;
                        case 7:
                            registerInventory();
                            break;
                        case 8:
                            deleteInventory();
                            break;
                        case 9:
                            performList();
                            break;
                        case 0:
                            System.out.println("프로그램 종료");
                            return;
                        default:
                            System.out.println("잘못된 입력입니다.");
                    }
                    break;
                    // 폐기 관리
                case 4:
                    System.out.println("[1]폐기 현황 [2]폐기 등록 [3]종료");
                    int choicew1 = sc.nextInt();
                    sc.nextLine();
                    switch (choicew1) {
                        case 1:
                            List<Waste> wasteList = wasteDao.wasteList();
                            System.out.println("======== 폐기 목록 조회 =========");
                            for (Waste waste : wasteList) System.out.println(waste);
                            break;
                        case 2:
                            boolean isSuccess = wasteDao.insertwaste(wasteDao.regWaste());
                            System.out.println("폐기 등록 : " + (isSuccess ? "성공" : "실패"));
                            break;
                        // 종료
                        case 3:
                            return;
                    }
                    break;
                case 5:
                    return;
                case 6:
                    List<S1Waste> s1WasteList = s1WastDao.s1production();
                    System.out.println("======== 대상 정보 조회 =========");
                    for (S1Waste s1Waste : s1WasteList) System.out.println(s1Waste);
                    break;
                case 7:
                    List<S2Step> s2StepList = s2StepDao.s2StepList();
                    System.out.println("======== 대상 정보 조회 =========");
                    for (S2Step s2Step : s2StepList) System.out.println(s2Step);
                    break;
                case 8:
                    List<S3Production> s3ProductionList = s3ProductionDao.s3ProductionList();
                    System.out.println("======== 대상 정보 조회 =========");
                    for (S3Production s3Production : s3ProductionList) System.out.println(s3Production);
                    break;

            }

        }

    }
    //자재 관리 메서드
    private void registerMaterial() {
        System.out.print("자재 코드: ");
        String code = sc.nextLine();
        System.out.print("자재 이름: ");
        String name = sc.nextLine();
        System.out.print("단위 (정수): ");
        int unit = sc.nextInt();
        sc.nextLine();
        System.out.print("유통기한 (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();

        try {
            java.sql.Date expDate = java.sql.Date.valueOf(dateStr);
            Material material = new Material(code, name, unit, expDate.toLocalDate());
            if (materialDao.insertMaterial(material)) {
                System.out.println("자재가 등록되었습니다.");
            } else {
                System.out.println("자재 등록에 실패했습니다.");
            }
        } catch (Exception e) {
            System.out.println("입력한 날짜 형식이 올바르지 않습니다.");
        }
    }

    private void updateMaterial() {
        System.out.print("수정할 자재 코드: ");
        String code = sc.nextLine();

        // 자재 존재 확인용 리스트 조회 (필요시 별도 메서드로 변경 가능)
        List<Material> materials = materialDao.materialList();
        Material target = materials.stream()
                .filter(m -> m.getMaterial_CODE().equals(code))
                .findFirst().orElse(null);

        if (target == null) {
            System.out.println("해당 자재가 존재하지 않습니다.");
            return;
        }

        System.out.print("새 자재 이름: ");
        String name = sc.nextLine();
        System.out.print("새 단위 (정수): ");
        int unit = sc.nextInt();
        sc.nextLine();
        System.out.print("새 유통기한 (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();

        try {
            java.sql.Date expDate = java.sql.Date.valueOf(dateStr);
            target.setMaterial_NAME(name);
            target.setUnit(unit);
            target.setExpiration_DATE(expDate.toLocalDate());

            if (materialDao.updateMaterial(target)) {
                System.out.println("자재가 수정되었습니다.");
            } else {
                System.out.println("자재 수정에 실패했습니다.");
            }
        } catch (Exception e) {
            System.out.println("입력한 날짜 형식이 올바르지 않습니다.");
        }
    }

    private void deleteMaterial() {
        System.out.print("삭제할 자재 코드: ");
        String code = sc.nextLine();

        if (materialDao.deleteMaterial(code)) {
            System.out.println("자재가 삭제되었습니다.");
        } else {
            System.out.println("자재 삭제에 실패했습니다.");
        }
    }

    private void findExpiringMaterials() {
        System.out.print("유통기한 남은 날짜 수 입력: ");
        int days = sc.nextInt();
        sc.nextLine();

        List<Material> expiringMaterials = materialDao.findMaterialsExpiringWithin(days);
        System.out.println("======== 유통기한 임박 자재 목록 ========");
        if (expiringMaterials.isEmpty()) {
            System.out.println("조건에 맞는 자재가 없습니다.");
        } else {
            expiringMaterials.forEach(System.out::println);
        }
    }
    private void materialList() {
        List<Material> materials = materialDao.materialList();
        if (materials.isEmpty()) {
            System.out.println("등록된 자재가 없습니다");
            return;
        }
        for (Material e : materials) {
            System.out.println(e);
        }
    }

    private void inventList() {
        List<Inventory> inventories = inventoryDao.inventoryList();
        if (inventories.isEmpty()) {
            System.out.println("등록된 재고가 없습니다");
            return;
        }
        for (Inventory e : inventories) {
            System.out.println(e);
        }
    }

    private void registerInventory() {
        System.out.print("창고 ID: ");
        String id = sc.nextLine();
        System.out.print("자재 코드(동일한 자재코드 입력): ");
        String code = sc.nextLine();
        System.out.print("단위 (정수): ");
        String unit = sc.nextLine();
        System.out.print("입고날짜 (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();
        System.out.print("자재 이름: ");
        String name = sc.nextLine();

        try {
            java.sql.Date stockDate = java.sql.Date.valueOf(dateStr);
            Inventory inventory = new Inventory(id, code, unit, stockDate.toLocalDate(), name);
            if (inventoryDao.insertInventory(inventory)) {
                System.out.println("창고에 재고가 입고되었습니다.");
            } else {
                System.out.println("입고에 실패했습니다.");
            }
        } catch (Exception e) {
            System.out.println("입력한 날짜 형식이 올바르지 않습니다.");
        }
    }

    private void deleteInventory() {
        System.out.print("출고할 자재 코드(동일한 자재코드 입력): ");
        String code = sc.nextLine();

        if (inventoryDao.deleteInventory(code)) {
            System.out.println("창고에 재고가 출고되었습니다.");
        } else {
            System.out.println("출고에 실패했습니다.");
        }
    }

    private void performList() {
        List<Performance> performanceList = performDao.performList();
        if (performanceList.isEmpty()) {
            System.out.println("등록된 설비 상태 조회가 없습니다");
            return;
        }
        for (Performance e : performanceList) {
            System.out.println(e);
        }
    }

}
