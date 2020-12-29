package fr.stazi.epsi.spring.boot.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.stazi.epsi.spring.boot.dto.DtoException;
import fr.stazi.epsi.spring.boot.dto.DtoSuccess;
import fr.stazi.epsi.spring.boot.dto.DtoUpdateCell;
import fr.stazi.epsi.spring.boot.entity.Cell;
import fr.stazi.epsi.spring.boot.entity.user.User;
import fr.stazi.epsi.spring.boot.exception.NotFoundException;
import fr.stazi.epsi.spring.boot.managers.CellManager;
import fr.stazi.epsi.spring.boot.managers.UserManager;

@RestController
@Transactional
@RequestMapping("/api/cell")
public class CellController {
	private CellManager cellManager;
	private UserManager userManager;
	
	public CellController(CellManager cellManager) {
		this.cellManager = cellManager;
	}
	
	@GetMapping("all/{userId}")
	public ResponseEntity<?> getCells(@PathVariable Long userId) {
		try {
			User user = this.userManager.getUserById(userId);
			return ResponseEntity.ok(user.getCells());
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(new DtoException(e.getMessage()));
		}
	}
	
	@PreAuthorize("@securityMethods.canManager(principal, #cellId)")
	@PutMapping("update/{cellId}")
	public ResponseEntity<?> updateCell(@RequestBody @Valid DtoUpdateCell dtoUpdateCell, BindingResult resValid) {
		if(!resValid.hasErrors()) {
			try {
				User user = this.userManager.getUserById(dtoUpdateCell.getUserId());
				Cell cell = this.cellManager.getCellById(user, dtoUpdateCell.getCellId());
				this.cellManager.updateCell(cell);
			} catch (NotFoundException e) {
				return ResponseEntity.badRequest().body(new DtoException(e.getMessage()));
			}
			return ResponseEntity.ok(new DtoSuccess("La mise à jour a été effectué avec succès"));
		} else {
			return ResponseEntity.badRequest().body(new DtoException("Une erreur est survenue"));
		}
	}
	
	@PreAuthorize("@securityMethods.canManager(principal, #cellId)")
	@PutMapping("update/{cellId}")
	public ResponseEntity<?> deleteCell(@RequestBody @Valid DtoUpdateCell dtoUpdateCell, BindingResult resValid) {
		if(!resValid.hasErrors()) {
			try {
				User user = this.userManager.getUserById(dtoUpdateCell.getUserId());
				Cell cell = this.cellManager.getCellById(user, dtoUpdateCell.getCellId());
				this.cellManager.updateCell(cell);
			} catch (NotFoundException e) {
				return ResponseEntity.badRequest().body(new DtoException(e.getMessage()));
			}
			return ResponseEntity.ok(new DtoSuccess("La mise à jour a été effectué avec succès"));
		} else {
			return ResponseEntity.badRequest().body(new DtoException("Une erreur est survenue"));
		}
	}
}
