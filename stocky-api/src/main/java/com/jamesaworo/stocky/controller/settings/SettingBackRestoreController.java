package com.jamesaworo.stocky.controller.settings;

import com.jamesaworo.stocky.dto.request.settings.SettingRequestDto;
import com.jamesaworo.stocky.service.settings.SettingBackupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

/**
 * @author Aworo James
 * @since 4/21/23
 */
@RestController
@RequestMapping(value = API_PREFIX + "/setting-backup")
@RequiredArgsConstructor
public class SettingBackRestoreController {
    private final SettingBackupService service;

    @GetMapping("/find")
    public ResponseEntity<SettingRequestDto> get(@RequestParam() String key) {
        return this.service.get(key);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<SettingRequestDto>> getAll() {
        return service.getAll();
    }

    @PostMapping(value = "update-all")
    public ResponseEntity<Boolean> updateAll(
            @RequestBody List<SettingRequestDto> list
    ) {
        return service.updateAll(list);
    }

    @PutMapping(value = "update")
    public ResponseEntity<Boolean> update(
            @RequestBody SettingRequestDto setting) {
        return service.update(setting);
    }

}
