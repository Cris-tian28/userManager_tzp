package service;

import dao.UserDao;
import entiy.PageBean;
import entiy.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 高内聚    低耦合
 * User: tzp
 * Date: 2020-07-15
 * Time: 20:23
 */
public class UserService {

    public User login(User loginUser) {
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        return user;
    }

    public int add(User user) {
        UserDao userDao = new UserDao();
        int ret = userDao.add(user);
        return ret;
    }

    public int delete(int id) {
        UserDao userDao = new UserDao();
        int ret = userDao.delete(id);
        return ret;
    }

    public  User  find(int id) {
        UserDao userDao = new UserDao();
        User user = userDao.find(id);
        return user;
    }

    public  int updateUser(User upUser) {
        UserDao userDao = new UserDao();
        int ret = userDao.updateUser(upUser);
        return ret;
    }

    /**
     * @param currentPage 当前页
     * @param rows 每页的行数
     * @param map  包含 name address email  根据map当中的条件  进行查询
     * @return
     *
     * 确定
     *     private int totalCount;   //总记录数     12   findAllRecord  完成
     *     private int totalPage; //总页码          3      完成
     *     private List<T> list;  //每页中的数据 ---》  findByPage  完成
     *     private int currentPage;  //当前页码   已知的          完成
     *     private int rows;   //每页的记录数       5        完成
     *
     */
    public PageBean<User> findAllByPage(int currentPage, int rows, Map<String, String[]> map) {

        PageBean<User> pageBean = new PageBean<>();

        UserDao userDao = new UserDao();
        int totalCount = userDao.findAllRecord(map);
        int totalPage = 0;

        if(totalCount % rows != 0) {
            totalPage = totalCount / rows + 1;
        }else {
            totalPage = totalCount / rows;
        }
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalCount(totalCount);
        /**
         * 当前假设是第一页：(1-1)*5 = 0
         * (2-1)*5 = 5
         */
        int start = (currentPage-1)*rows;

        List<User> userList = userDao.findByPage(start,rows,map);
        pageBean.setList(userList);
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        return pageBean;
    }





}
