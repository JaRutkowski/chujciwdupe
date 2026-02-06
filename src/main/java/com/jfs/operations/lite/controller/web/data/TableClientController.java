package com.jfs.operations.lite.controller.web.data;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.client.TableClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/web/app/table/client")
@RequiredArgsConstructor
public class TableClientController {
	private final RestClient restClient;
	private final HttpServletRequest httpServletRequest;

	@GetMapping
	public String tableClient(Model model) {
		List<TableClientDto> tableClientDtos = (List<TableClientDto>) restClient.getSync("/api/client/table", List.class, httpServletRequest);
		model.addAttribute("tableClients", tableClientDtos);
		return "table-client-form";
	}
}
