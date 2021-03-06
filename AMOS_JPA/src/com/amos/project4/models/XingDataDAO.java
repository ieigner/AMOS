/*
 *
 * This file is part of the Datev and Social Media project.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.amos.project4.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.amos.project4.socialMedia.Xing.XingDataType;

public class XingDataDAO {
	
	private static final String PERSISTENCE_UNIT_NAME = "AMOS_JPA";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	private static XingDataDAO instance;

	public static XingDataDAO getInstance() {
		if (instance == null) {
			instance = new XingDataDAO();
		}
		return instance;
	}
	
	private XingDataDAO() {
		super();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public synchronized List<XingData> getAllXingData(){
		em = factory.createEntityManager();
		Query q = em.createQuery("SELECT d FROM XingData ORDER BY d.ID");
		@SuppressWarnings("unchecked")
		List<XingData> rslt = q.getResultList();
		em.close();
		return rslt;
	}
	
	public synchronized XingData getXingData(Integer data_id){
		if(data_id == null || data_id == 0) return null;
		em = factory.createEntityManager();
		XingData rslt = em.find(XingData.class, data_id);
		em.close();
		return rslt;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<XingData> getAllXingDataOfClient(Integer owner_id){
		if(owner_id == null || owner_id == 0) return new ArrayList<XingData>();
		em = factory.createEntityManager();
		Client owner = em.find(Client.class, owner_id);
		Query q = em.createQuery("SELECT d FROM XingData d WHERE d.owner = :paramid ORDER BY d.ID");
		q.setParameter("paramid", owner);
		List<XingData> rslt = q.getResultList();
		em.close();
		return rslt;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<XingData> getAllXingDataOfClientByType(Integer owner_id, XingDataType type){
		if(owner_id == null || owner_id == 0) return new ArrayList<XingData>();
		em = factory.createEntityManager();
		Client owner = em.find(Client.class, owner_id);
		Query q = em.createQuery("SELECT d FROM XingData d WHERE d.owner = :paramid and d.type = :paramtype ORDER BY d.ID");
		q.setParameter("paramid", owner);
		q.setParameter("paramtype", type.toString());
		List<XingData> rslt = q.getResultList();
		em.close();
		return rslt;
	}
	
	public synchronized boolean persistXingData(XingData data) {
		if(data == null) return false;
		try {
			em = factory.createEntityManager();
			em.getTransaction().begin();
			
			em.persist(data);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			return false;
		}finally{
		}
		
		
	}
	
	public synchronized boolean updateXingData(XingData data){
		if(data == null) return false;
		try{
			em = factory.createEntityManager();
			em.getTransaction().begin();
			em.persist(data);
			
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception e){
			em.getTransaction().rollback();
			em.close();
			return false;
		}finally{
		}
	}
	
	public synchronized boolean deleteXingData(XingData data){
		if(data == null) return false;
		try{
			em = factory.createEntityManager();
			em.getTransaction().begin();
			XingData tmp = em.find(XingData.class,data.getID() );
			em.merge(tmp);
			em.remove(tmp);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception e){
			em.getTransaction().rollback();
			em.close();
			return false;
		}finally{
		}
	}
	
	public synchronized boolean deleteXingDatas(Client client,XingDataType type){
		if(client == null  || type == null) return false;
		try{
			List<XingData> datas = this.getAllXingDataOfClientByType(client.getID(), type);
			for(XingData data : datas){
				this.deleteXingData(data);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{

		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized boolean deleteAllXingData(Integer owner_id){
		if(owner_id == null || owner_id == 0) return false;
		em = factory.createEntityManager();
		Client owner = em.find(Client.class, owner_id);
		Query q = em.createQuery("SELECT d FROM XingData d WHERE d.owner = :paramid ORDER BY d.ID");
		q.setParameter("paramid", owner);
		List<XingData> rslt = q.getResultList();
		for (XingData data : rslt) {
			if(! deleteXingData(data)) return false;
		}
		em.close();
		return true;
	}
}
