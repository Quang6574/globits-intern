package com.globits.demo.service.implement;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.globits.demo.repository.TaskRepository;
import com.globits.demo.dto.TaskExcelDTO;
import com.globits.demo.mapper.TaskMapper;
import com.globits.demo.model.Task;

import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.springframework.stereotype.Service;

@Service
public class TaskExport {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskExport(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    // Separate method to prepare workbook/worksheet and header
    private Worksheet createWorksheet(Workbook wb) {
        Worksheet ws = wb.newWorksheet("Task_List");
        ws.width(0, 25);
        ws.width(1, 15);

        ws.range(0, 0, 0, 6)
                .style()
                .fontName("Arial")
                .fontSize(16)
                .bold()
                .fillColor("3366FF")
                .set();

        ws.value(0, 0, "Project Name");
        ws.value(0, 1, "Description");
        ws.value(0, 2, "Start Time");
        ws.value(0, 3, "End Time");
        ws.value(0, 4, "Priority");
        ws.value(0, 5, "Status");
        ws.value(0, 6, "Assigned Person ID");

        return ws;
    }

    public void exportToExcel(OutputStream os) throws IOException {
        //Fetch data
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found to export.");
            return;
        }

        List<TaskExcelDTO> taskExcelDTOs = taskMapper.toExcelDtoList(tasks);
        //  Create workbook
        try (Workbook wb = new Workbook(os,
                "globitsProject", "1.0")) {

            //goi function tao worksheet o workbook
            Worksheet ws = createWorksheet(wb); // call separate method

            //bat dau tu row 1
            int row = 1;
            for (TaskExcelDTO dto : taskExcelDTOs) {
                int col = 0;
                ws.value(row, col++, dto.getName());
                ws.value(row, col++, dto.getDescription());
                ws.value(row, col++, dto.getStartDate());    // adapt type if needed
                ws.value(row, col++, dto.getEndDate());
                ws.value(row, col++, dto.getPriority());
                ws.value(row, col++, dto.getStatus());
                ws.value(row, col++, dto.getPersonId());
                row++;
            }

        }
    }


}
