package fr.stazi.epsi.spring.boot.managers;

import java.util.Optional;

import fr.stazi.epsi.spring.boot.entity.Cell;
import fr.stazi.epsi.spring.boot.entity.user.User;
import fr.stazi.epsi.spring.boot.exception.NotFoundException;
import fr.stazi.epsi.spring.boot.repository.CellRepository;

public class CellManager {
	private CellRepository cellRepo;
	
	public CellManager(CellRepository cellRepo) {
		this.cellRepo = cellRepo;
	}
	
	public void removeCell(Cell cell) {
		this.cellRepo.delete(cell);
	}
	
	public void updateCell(Cell cell) {
		this.cellRepo.save(cell);
	}
	
	public Cell getCellByUserAndId(User user, Long idCell) throws NotFoundException {
		Optional<Cell> cell = user.getCells().stream().filter((c) -> c.getId() == idCell).findAny();
		if(cell.isPresent()) {
			return cell.get();
		} else {
			throw new NotFoundException();
		}
		
	}
}
