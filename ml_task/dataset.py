from torch.utils.data import Dataset
import pandas as pd
import torch

class VoicePassingDataset(Dataset):

    def __init__(self, small = False):
        super(VoicePassingDataset, self).__init__()

        dataFrame = pd.read_excel("../data_task/dataset/final_data.xlsx", index_col=0)
        if small: # small dataset for test only
            dataFrame = dataFrame.sample(n = 100, ignore_index = True, random_state = 42, replace = False)
        self.dataFrame = dataFrame

    def __getitem__(self, idx):
        text, label, _ = self.dataFrame.iloc[idx].values
        label = torch.LongTensor([label])

        return text, label
    
    def __len__(self):
        return len(self.dataFrame)