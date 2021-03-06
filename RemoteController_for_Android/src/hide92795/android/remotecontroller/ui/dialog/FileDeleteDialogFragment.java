package hide92795.android.remotecontroller.ui.dialog;

import hide92795.android.remotecontroller.R;
import hide92795.android.remotecontroller.receivedata.DirectoryData.File;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class FileDeleteDialogFragment extends DialogFragment {
	private Callback callback;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if (getTargetFragment() != null && getTargetFragment() instanceof Callback) {
			callback = (Callback) getTargetFragment();
		} else if (getActivity() != null && getActivity() instanceof Callback) {
			callback = (Callback) getActivity();
		}
		final File file = getArguments().getParcelable("FILE");
		int message = file.isDirectory() ? R.string.str_confirm_delete_directory : R.string.str_confirm_delete_file;

		Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.str_delete);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(getString(message) + "\n" + file.getName());
		builder.setPositiveButton(R.string.str_yes, new OnClickListener() {
			public void onClick(DialogInterface dialog, int select) {
				callback.onFileDeleteSelected(file);
			}
		});
		builder.setNegativeButton(R.string.str_no, null);
		return builder.create();
	}
	public interface Callback {
		void onFileDeleteSelected(File file);
	}
}
