package springbook.user.sqlService;

public class SqlAdminService implements AdminEventListener {

    private UpdatableSqlRegistry updatableSqlRegistry;

    public void setUpdatableSqlRegistry(UpdatableSqlRegistry updatableSqlRegistry) {
        this.updatableSqlRegistry = updatableSqlRegistry;
    }

    @Override
    public void updateEventListener(UpdateEvent event) {
        String KEY_ID = "KEY_ID";
        String SQL_ID = "SQL_ID";
        updatableSqlRegistry.updateSql(event.get(KEY_ID), event.get(SQL_ID));
    }
}
