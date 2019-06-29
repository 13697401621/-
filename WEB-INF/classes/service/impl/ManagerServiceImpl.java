package service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.ManagerDao;
import entity.Manager;
import service.ManagerService;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ManagerServiceImpl implements ManagerService {

	private ManagerDao managerDao;

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public Manager loginManager(Manager manager) {
		return managerDao.loginManager(manager);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void saveManager(Manager musername) {
		managerDao.saveManager(musername);

	}

	@Override
	public List<Manager> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> queryUsers(String musername) {
		if (musername == null || "".equals(musername))
			return managerDao.findAllUsers();
		else
			return managerDao.queryByUsername(musername);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateManager(Manager manager) {
		String aa = this.managerDao.update(manager);

		if (aa == null) {
			return true;
		} else {
			return false;
		}

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void deleteManager(String musername) {
		managerDao.delete(musername);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public Manager getManager(String musername) {

		return managerDao.getManager(musername);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public Manager queryUpUsers(String musername) {

		return this.managerDao.queryUpUsers(musername);
	}

}
